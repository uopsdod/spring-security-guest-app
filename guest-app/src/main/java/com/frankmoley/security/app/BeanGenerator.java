package com.frankmoley.security.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class BeanGenerator {
	
	@Bean
	public ApplicationSecurityConfiguration applicationSecurityConfiguration() {
		return new ApplicationSecurityConfiguration();
	}
	
}
