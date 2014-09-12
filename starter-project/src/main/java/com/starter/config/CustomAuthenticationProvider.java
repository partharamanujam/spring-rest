package com.starter.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.starter.service.UserAuthService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	private UserAuthService userAuthService = new UserAuthService();

	@Override
	public Authentication authenticate(Authentication authentication) {
		String user = authentication.getName();
		String pass = authentication.getCredentials().toString();
		if (userAuthService.checkPass(user, pass)) {
			logger.info("login success for: " + user);
			List<GrantedAuthority> grantedAuths = new ArrayList<>(); // TODO: update for RBAC
			return new UsernamePasswordAuthenticationToken(user, pass, grantedAuths);
		} else {
			logger.error("login failed for: " + user);
			throw new AuthenticationServiceException("authetication failed: " + user);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
