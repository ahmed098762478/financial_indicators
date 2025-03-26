package com.gov.cmr.transparisation_module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc  // ensures your custom configuration overrides default
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Applies to any endpoint under /api/**
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200") // must not be "*"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                // If you don't need cookies or Authorization headers, set false:
                .allowCredentials(true);

        // Add more mappings if needed
    }
}
