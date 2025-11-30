package com.examly.springapp.config;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class for handling JWT authentication.
 * Extends OncePerRequestFilter to ensure that the filter is executed once per request.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired // NOSONAR
    private JwtUtils utils;

    @Autowired // NOSONAR
    private MyUserDetailsService service;

    private static Logger myLogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private String jwtToken;
    private String username;
    private UserDetails userDetails;

    private static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    private static final String BEARER = "Bearer ";

    /**
     * Filters each request to check for a valid JWT token and set authentication details in the security context.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If a servlet error occurs.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        myLogger.info("INSIDE THE doFilterInternal METHOD IN JwtAuthenticationFilter");
        boolean valid = checkUsername(request);
        if (valid) {
            valid = validateUser();
        }
        if (valid) {
            setCredentials(request);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Checks the username from the JWT token in the request header.
     *
     * @param request The HTTP request.
     * @return true if the username is successfully extracted, false otherwise.
     */
    private boolean checkUsername(HttpServletRequest request) {
        myLogger.info("CHECKING USERNAME INSIDE JwtAuthenticationFilter");
        String authheader = request.getHeader(AUTHORIZATION);
        myLogger.info("Authorization header: {}", authheader);
        if (authheader == null || !authheader.startsWith(BEARER)) {
            myLogger.warn("Authorization header is missing or does not start with 'Bearer '");
            return false;
        }
        this.jwtToken = authheader.substring(7);
        myLogger.info("Extracted JWT token: {}", this.jwtToken);
        this.username = utils.extractEmail(this.jwtToken);
        myLogger.info("Extracted username from token: {}", this.username);
        return true;
    }

    /**
     * Validates the user details using the JWT token.
     *
     * @return true if the user is valid, false otherwise.
     */
    private boolean validateUser() {
        myLogger.info("VALIDATING USER INSIDE JwtAuthenticationFilter");
        if (this.username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return false;
        }
        this.userDetails = service.loadUserByUsername(username);
        return utils.validateToken(this.jwtToken, this.userDetails);
    }

    /**
     * Sets the authentication credentials in the security context.
     *
     * @param request The HTTP request.
     */
    private void setCredentials(HttpServletRequest request) {
        myLogger.info("SETTING CREDENTIALS INSIDE JwtAuthenticationFilter");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(this.userDetails, null, this.userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
