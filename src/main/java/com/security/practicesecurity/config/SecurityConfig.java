package com.security.practicesecurity.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.security.practicesecurity.config.auth.AuthFailureHandler;

@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
				.antMatchers("/", "/index")
				.authenticated()
				
				.anyRequest()
				.permitAll()
				
				.and()
				
				.formLogin()
				.loginPage("/auth/signin")
				.loginProcessingUrl("/auth/signin")
				.failureHandler(new AuthFailureHandler())
				.defaultSuccessUrl("/");
	}
	
}
