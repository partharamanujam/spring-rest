package com.starter.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starter.model.User;
import com.starter.service.UserService;
import com.starter.utils.AuditLogger;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('CREATE')")
	public @ResponseBody User createUser(@RequestBody User user, HttpServletRequest request) {
		AuditLogger.getInstance().logRequest(request, user);
		return userService.createUser(user);
	}

	// READ
	@RequestMapping(value = "{uid}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('READ')")
	public @ResponseBody User getUser(@PathVariable String uid) {
		return userService.readUser(uid);
	}

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('UPDATE')")
	public @ResponseBody User updateUser(@RequestBody User user, HttpServletRequest request) {
		AuditLogger.getInstance().logRequest(request, user);
		return userService.updateUser(user);
	}

	// DELETE
	@RequestMapping(value = "{uid}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('DELETE')")
	public @ResponseBody User deleteUser(@PathVariable String uid, HttpServletRequest request) {
		if (request.getUserPrincipal().getName().compareTo(uid) == 0) {
			throw new UnsupportedOperationException("cannot delete self");
		} else {
			AuditLogger.getInstance().logRequest(request, uid);
			return userService.deleteUser(uid);
		}
	}

	// PERMISSIONS
	@RequestMapping(value = "perms/{uid}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('PERMS')")
	public @ResponseBody User updatePermissions(@PathVariable String uid, @RequestBody String[] permissions,
			HttpServletRequest request) {
		if (request.getUserPrincipal().getName().compareTo(uid) == 0) {
			throw new UnsupportedOperationException("cannot add permissions to self");
		} else {
			AuditLogger.getInstance().logRequest(request, uid + " = " + StringUtils.join(permissions, "|"));
			return userService.updatePermissions(uid, permissions);
		}
	}
}