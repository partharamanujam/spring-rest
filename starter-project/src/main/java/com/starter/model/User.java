package com.starter.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Users")
@org.hibernate.annotations.Entity(dynamicInsert = true)
@XmlRootElement
public class User {
	@Id
	private String uid;
	private String fname;
	private String lname;
	private int age;
	private String pass;

	public void update(User user) {
		if(user.uid != null) {
			this.uid = user.uid;
		}
		if(user.fname != null) {
			this.fname = user.fname;
		}
		if(user.lname != null) {
			this.lname = user.lname;
		}
		if(user.age != 0) {
			this.age = user.age;
		}
		if(user.pass != null) {
			this.pass = user.pass;
		}
	}

	@XmlElement
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@XmlElement
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@XmlElement
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
