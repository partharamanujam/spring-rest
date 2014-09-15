package com.starter.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.starter.service.UserAuthService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	private UserAuthService userAuthService = new UserAuthService();

	@Override
	public Authentication authenticate(Authentication authentication) {
		String uid = authentication.getName();
		String pass = authentication.getCredentials().toString();
		if (userAuthService.checkPass(uid, pass)) {
			logger.info("login success for: " + uid);
			String[] permissions = userAuthService.getPermissions(uid);
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			for (int idx = 0; idx < permissions.length; idx++) {
				grantedAuths.add(new SimpleGrantedAuthority(permissions[idx]));
			}
			return new UsernamePasswordAuthenticationToken(uid, pass, grantedAuths);
		} else {
			logger.error("login failed for: " + uid);
			throw new AuthenticationServiceException("authetication failed: " + uid);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
