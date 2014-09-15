package com.starter.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.QueryException;
import org.springframework.stereotype.Service;

import com.starter.model.User;
import com.starter.utils.HibernatePojoEntity;

@Service
public class UserService {
	HibernatePojoEntity<User> entity = new HibernatePojoEntity<User>(User.class);

	private User checkUserAndHashPassword(User user) {
		if (user != null) {
			user.setPass("*****");
		} else {
			throw new QueryException("user not found");
		}
		return user;
	}

	public User getUpdateUser(User user) {
		// do not update permissions
		User updateUser = entity.read(user.getUid());
		if (user.getFname() != null) {
			updateUser.setFname(user.getFname());
		}
		if (user.getLname() != null) {
			updateUser.setLname(user.getLname());
		}
		if (user.getAge() != 0) {
			updateUser.setAge(user.getAge());
		}
		if (user.getPass() != null) {
			updateUser.setPass(user.getPass());
		}
		return updateUser;
	}

	public User createUser(User user) {
		user.setPerms("READ|UPDATE"); // allow READ & UPDATE by default
		user = entity.create(user);
		return checkUserAndHashPassword(user);
	}

	public User readUser(String uid) {
		User user = entity.read(uid);
		return checkUserAndHashPassword(user);
	}

	public User updateUser(User user) {
		User updateUser = getUpdateUser(user);
		updateUser = entity.update(updateUser);
		return checkUserAndHashPassword(updateUser);
	}

	public User deleteUser(String uid) {
		User user = entity.read(uid);
		user = entity.delete(user);
		return checkUserAndHashPassword(user);
	}

	public User updatePermissions(String uid, String[] permissions) {
		User user = readUser(uid);
		user.setPerms(StringUtils.join(permissions, "|"));
		user = entity.update(user);
		return checkUserAndHashPassword(user);
	}
}
