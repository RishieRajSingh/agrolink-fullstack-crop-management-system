package com.examly.springapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired // NOSONAR
    private MyUserDetailsService myService; // NOSONAR

    @Autowired // NOSONAR
    private JwtAuthenticationFilter myJwtAuthFilter; // NOSONAR

    private static Logger myLogger = LoggerFactory.getLogger(SecurityConfig.class);

    private static final String[] WHITELIST ={
        "/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/api/login","/api/register"
    };

    /**
     * Configures the security filter chain.
     * 
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        myLogger.info("IN FILTER CHAIN FROM SecurityConfig");
        return http
                .cors()
                .and()
                .csrf(c -> c.disable())
                .authorizeHttpRequests(request -> request
                    .requestMatchers(WHITELIST).permitAll()
                    .anyRequest().authenticated())
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(myJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean for password encoding.
     * 
     * @return The PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for authentication provider.
     * 
     * @return The AuthenticationProvider bean.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        myLogger.info("INSIDE AuthenticationProvider() FROM SecurityConfig");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        myLogger.info("SETTING USER DETAILS SERVICE FOR THE DaoAuthenticationProvider object TO myService, WHICH CAME FROM DEPENDENCY INJECTION");
        authenticationProvider.setUserDetailsService(myService);
        myLogger.info("SETTING PASSWORD ENCODER FOR DaoAuthenticationProvider OBJECT TO passwordEncoder() METHOD FROM SecurityConfig");
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean for authentication manager.
     * 
     * @param config The AuthenticationConfiguration object.
     * @return The AuthenticationManager bean.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean for authentication entry point.
     * 
     * @return The AuthenticationEntryPoint bean.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }
}