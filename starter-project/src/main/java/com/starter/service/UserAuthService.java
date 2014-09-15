package com.starter.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.starter.model.UserAuth;
import com.starter.utils.HibernatePojoEntity;

@Service
public class UserAuthService {
	HibernatePojoEntity<UserAuth> entity = new HibernatePojoEntity<UserAuth>(UserAuth.class);

	public boolean checkPass(String uid, String pass) {
		UserAuth user = entity.read(uid);
		return ((user != null) && (user.getPass().compareTo(pass) == 0));
	}
	
	public String[] getPermissions(String uid) {
		UserAuth user = entity.read(uid);
		return StringUtils.split(user.getPerms(), "|");
	}
}
