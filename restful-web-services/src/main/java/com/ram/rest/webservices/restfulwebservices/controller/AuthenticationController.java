package com.ram.rest.webservices.restfulwebservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ram.rest.webservices.restfulwebservices.bean.AuthenticationBean;
import com.ram.rest.webservices.restfulwebservices.bean.HelloWorldBean;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AuthenticationController {
	

	@GetMapping(path ="/basicauth")
	public AuthenticationBean helloWorldBean(){

		
		return new AuthenticationBean("you are authnticated");
		
	}

}
