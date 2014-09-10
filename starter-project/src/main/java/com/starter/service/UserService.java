package com.starter.service;

import org.springframework.stereotype.Service;

import com.starter.model.User;
import com.starter.utils.HibernateEntity;

@Service
public class UserService {
	HibernateEntity<User> entity = new HibernateEntity<User>(User.class);

	public User createUser(User user) {
		user = entity.create(user);
		if(user != null) {
			user.setPass("*****");
		}
		return user;
	}

	public User readUser(String id) {
		User user = entity.read(id);
		if(user != null) {
			user.setPass("*****");
		}
		return user;
	}

	public User updateUser(User user) {
		user = entity.update(user);
		if(user != null) {
			user.setPass("*****");
		}
		return user;
	}

	public User deleteUser(User user) {
		user = entity.delete(user);
		if(user != null) {
			user.setPass("*****");
		}
		return user;
	}

}