package com.codebag.ekart.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codebag.ekart.filters.JwtTokenFilter;
import com.codebag.ekart.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found.")));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/auth/*").permitAll().antMatchers("/checkStatus").permitAll().anyRequest()
				.authenticated();

		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
