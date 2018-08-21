package com.frankmoley.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
	
}
