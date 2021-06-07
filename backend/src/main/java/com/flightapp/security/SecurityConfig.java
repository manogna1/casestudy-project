package com.flightapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
		http.cors();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");	}
	
}
