package com.security.practicesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.practicesecurity.config.auth.AuthFailureHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/api/v1/grant/test/user/**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				
				.antMatchers("/api/v1/grant/test/manager/**")
				.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				
				.antMatchers("/api/v1/grant/test/admin/**")
				.access("hasRole('ROLE_ADMIN')")
		
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
