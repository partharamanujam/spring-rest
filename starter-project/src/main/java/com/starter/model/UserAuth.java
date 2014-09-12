package com.starter.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class UserAuth {
	@Id
	private String uid;
	private String pass;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
