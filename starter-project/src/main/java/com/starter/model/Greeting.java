package com.starter.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Greeting {

	@XmlElement
	private final long id;
	@XmlElement
	private final String content;

	public Greeting() {
		this.id = 0;
		this.content = "Hello, world";
	}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
