package com.starter.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {
	private final Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		logger.info("logout for: " + request.getUserPrincipal().getName());
		request.logout();
	}
}
