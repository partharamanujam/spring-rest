package com.starter.controller;

import org.hibernate.QueryException;
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
@RequestMapping(value = "/user/{uid}")
public class UserController {
	@Autowired
	UserService userService;

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User createUser(@PathVariable String uid, @RequestBody User user) {
		user.setUid(uid);
		return userService.createUser(user);
	}

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String uid) {
		User user = userService.readUser(uid);
		if(user != null) {
			return user;
		} else {
			throw new QueryException("user not found");
		}
	}

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody User updateUser(@PathVariable String uid, @RequestBody User user) {
		User updateUser = getUser(uid);
		if (updateUser != null) {
			updateUser.update(user);
			return userService.updateUser(updateUser);
		} else {
			throw new QueryException("user not found");
		}
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@PathVariable String uid) {
		User deleteUser = getUser(uid);
		if(deleteUser != null) {
			return userService.deleteUser(deleteUser);
		} else {
			throw new QueryException("user not found");
		}
	}
}