package com.election.electionbackend;  // Ensure this matches your package structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;  // Import SecurityAutoConfiguration for exclusion

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})  // Exclude Spring Security auto-config
public class ElectionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionBackendApplication.class, args);  // Run the Spring Boot application
	}
}
