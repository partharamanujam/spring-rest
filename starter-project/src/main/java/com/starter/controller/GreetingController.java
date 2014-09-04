package com.starter.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starter.model.Greeting;

@Controller
public class GreetingController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public @ResponseBody Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		logger.info("GET: /greeting");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
