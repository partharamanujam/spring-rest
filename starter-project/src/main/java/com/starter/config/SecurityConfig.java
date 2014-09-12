package com.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = false, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http = http.addFilterBefore(new RequestLoggingFilter(), ChannelProcessingFilter.class);
		// @formatter:off
		if (GlobalSettings.securityEnabled) {
			if (GlobalSettings.csrfEnabled) {
				if (GlobalSettings.csrfHeadersEnabled) {
					http = http.addFilterAfter(new CsrfHeadersFilter(), CsrfFilter.class);
				}
			} else {
				http = http.csrf().disable();
			}
			http
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/greeting").permitAll()
					.and()
				.authorizeRequests()
					.anyRequest().authenticated()
					.and()
				.formLogin();
		} else {
			http
				.csrf().disable()
				.authorizeRequests().anyRequest().permitAll();
		}
		// @formatter:on
	}
}
