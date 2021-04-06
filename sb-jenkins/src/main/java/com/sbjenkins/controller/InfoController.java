package com.sbjenkins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbjenkins.config.InstanceInfoService;


@RestController
public class InfoController {
	
	@Autowired
	private InstanceInfoService service;

	
	@GetMapping("/")
	public
	String sayHello(){
		return "hello there !";
	}
	@Value("${app.message}")
	private String envMessage;
	@Value("${server.port}")
	private int serverPort;
	
	@GetMapping(path = "/application")
	public String getApp() {
		return "Application running on " + service.retrieveInstanceInfo()+"on port : "+serverPort;
	}
}
