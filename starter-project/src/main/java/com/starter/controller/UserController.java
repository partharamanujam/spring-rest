package com.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starter.model.User;
import com.starter.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// READ
	@RequestMapping(value = "{uid}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String uid) {
		return userService.readUser(uid);
	}

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	// DELETE
	@RequestMapping(value = "{uid}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@PathVariable String uid) {
		return userService.deleteUser(uid);
	}
}