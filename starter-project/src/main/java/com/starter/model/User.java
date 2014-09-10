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

	@XmlElement
	public String getId() {
		return uid;
	}

	public void setId(String uid) {
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
