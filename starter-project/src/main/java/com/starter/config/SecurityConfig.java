package com.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
			http = http.authorizeRequests()
						.antMatchers(HttpMethod.GET, "/greeting").anonymous()
						.and();
		} else {
			http
				.csrf().disable()
				.authorizeRequests().anyRequest().permitAll();
		}
		// @formatter:on
	}
}
