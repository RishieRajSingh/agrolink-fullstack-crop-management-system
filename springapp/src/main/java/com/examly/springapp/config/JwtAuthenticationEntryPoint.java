package com.examly.springapp.config;
 
import java.io.IOException;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
/**
 * Entry point for handling authentication exceptions in JWT authentication.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
 
    private static Logger myLogger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
 
    /**
     * Handles authentication exceptions by sending an unauthorized error response.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param authException The authentication exception.
     * @throws IOException If an input or output error occurs.
     * @throws ServletException If a servlet error occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        myLogger.info("IN JwtAuthenticationEntryPoint");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendError(401, "Unauthorized");
    }
}