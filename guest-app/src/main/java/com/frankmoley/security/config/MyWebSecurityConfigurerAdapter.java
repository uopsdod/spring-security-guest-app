package com.frankmoley.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/","/index","/css/*","/js/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	
	/**
	 * remember to add @Bean to make it work
	 */
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("sam").password("password").roles("USER","ADMIN").build());
		users.add(User.withDefaultPasswordEncoder().username("tom").password("pass").roles("USER").build());
		return new InMemoryUserDetailsManager(users);
	}
	
	
	
}
