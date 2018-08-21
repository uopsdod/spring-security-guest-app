package com.frankmoley.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class GuestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestAppApplication.class, args);
	}
}
