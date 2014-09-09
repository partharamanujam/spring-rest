package com.starter.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestLoggingFilter extends OncePerRequestFilter {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> headerNames = req.getHeaderNames();
		StringBuilder strBldr = new StringBuilder();
		try {
			strBldr.append("REQ: " + req.getRequestURL().toString() + "?" + req.getQueryString() + "["
					+ req.getMethod() + "]");
			strBldr.append(" => IP: " + req.getRemoteAddr() + " & Cookie(s)");
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					strBldr.append(" {" + cookie.getName() + "::" + cookie.getValue() + "}");
				}
			}
			strBldr.append(" & Header(s)");
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = req.getHeader(key);
				strBldr.append(" {" + key + "::" + value + "}");
			}
			logger.info(strBldr.toString());
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			logger.error("ServletException/IOException : " + e.toString());
			if (e instanceof ServletException) {
				throw new ServletException(e);
			} else {
				throw new IOException(e);
			}
		}
	}
}
