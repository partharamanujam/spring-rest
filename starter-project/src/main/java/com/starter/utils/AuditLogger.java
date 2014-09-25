package com.starter.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuditLogger {
	private final Logger logger = Logger.getLogger(this.getClass());
	private static final AuditLogger instance = new AuditLogger();

	private AuditLogger() {
		super();
	}

	public static AuditLogger getInstance() {
		return instance;
	}

	private String getRequestData(HttpServletRequest request) {
		StringBuilder buffer = new StringBuilder();
		switch (request.getMethod()) {
		case "POST":
			buffer.append("CREATE");
			break;
		case "GET":
			buffer.append("READ");
			break;
		case "PUT":
			buffer.append("UPDATE");
			break;
		case "DELETE":
			buffer.append("DELETE");
			break;
		default:
			buffer.append("ERROR");
		}
		buffer.append(" - ");
		buffer.append(request.getRequestURI());
		return buffer.toString();
	}

	private String getUserName() {
		try {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		} catch (Exception ex) {
			return "uknown";
		}
	}

	public <T> void logRequest(HttpServletRequest request, T obj) {
		String user = getUserName();
		try {
			logger.info(user + " => " + getRequestData(request) + " # " + new ObjectMapper().writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			logger.info(user + " => " + getRequestData(request) + " # data-error");
			logger.debug(e.getStackTrace());
		}
	}

	public <T> void logRequest(Authentication authentication, HttpServletRequest request, T obj) {
		String user = authentication.getName();
		try {
			logger.info(user + " => " + getRequestData(request) + " # " + new ObjectMapper().writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			logger.info(user + " => " + getRequestData(request) + " # data-error");
			logger.debug(e.getStackTrace());
		}
	}

	public void logRequest(HttpServletRequest request, String data) {
		String user = getUserName();
		logger.info(user + " => " + getRequestData(request) + " # " + data);
	}

	public void logRequest(Authentication authentication, HttpServletRequest request, String data) {
		String user = authentication.getName();
		logger.info(user + " => " + getRequestData(request) + " # " + data);
	}

	public void logRequest(String user, String crud, String url, String data) {
		logger.info(user + " => " + crud + " - " + url + " # " + data);
	}
}
