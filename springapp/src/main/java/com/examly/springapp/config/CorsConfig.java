package com.examly.springapp.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) settings.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
 
    private static Logger myLogger = LoggerFactory.getLogger(CorsConfig.class);
 
    /**
     * Configures CORS mappings.
     *
     * @param registry The CorsRegistry to add CORS mappings to.
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        myLogger.info("IN CorsConfig");
        registry
        .addMapping("/**")
        .allowedOriginPatterns("https://*")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        .allowedHeaders("Authorization", "Content-Type","Access-Control-Allow-Origin","Accept")
        .allowCredentials(true);
    }
}
 