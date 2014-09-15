package com.starter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starter.model.User;
import com.starter.service.UserService;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		User user = userService.readUser(request.getUserPrincipal().getName());
		return "Welcome: " + user.getFname() + " " + user.getLname();
	}

}
