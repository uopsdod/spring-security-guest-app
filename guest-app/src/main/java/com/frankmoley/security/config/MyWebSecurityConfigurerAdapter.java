package com.frankmoley.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
	
	DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired
	public MyWebSecurityConfigurerAdapter(DaoAuthenticationProvider daoAuthenticationProvider) {
		super();
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/","/index","/css/*","/js/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login_page").permitAll()
            .loginProcessingUrl("/login").permitAll()
            .defaultSuccessUrl("/index", true)
            .and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout_success_page").permitAll()
			;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.daoAuthenticationProvider);
	}
	
	
	
}
