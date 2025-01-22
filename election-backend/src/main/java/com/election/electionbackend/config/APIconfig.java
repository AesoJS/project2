package com.election.electionbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class APIconfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Haal de frontend URL uit de omgevingsvariabele of gebruik een standaard waarde
        String frontendUrl = System.getenv("FRONTEND_URL");
        if (frontendUrl == null || frontendUrl.isEmpty()) {
            frontendUrl = "https://election-frontendd.onrender.com"; // Standaard Render frontend URL
        }

        // Stel CORS in voor alle endpoints
        registry.addMapping("/**")  // Toepassen op alle API endpoints
                .allowedOrigins(frontendUrl)  // Sta alleen toegang toe vanaf de frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);  // Sta credentials toe, bijvoorbeeld cookies of authorization headers
    }
}
