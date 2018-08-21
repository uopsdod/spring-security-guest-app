package com.frankmoley.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import com.frankmoley.security.app.auth.MyUserDetailsService;

@Configuration
public class SecurityBeanConfiguration {
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService myUserDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(11));
		return provider;
	}
	
}
