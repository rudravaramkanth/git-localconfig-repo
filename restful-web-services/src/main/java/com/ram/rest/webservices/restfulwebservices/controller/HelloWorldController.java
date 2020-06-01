package com.ram.rest.webservices.restfulwebservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ram.rest.webservices.restfulwebservices.bean.HelloWorldBean;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
	
	@GetMapping(path ="/hello")
	public String sayHello(){
		
		return "Hellow-World";
		
	}
	@GetMapping(path ="/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		// throw new RuntimeException("some error message");
		 
		
		return new HelloWorldBean("hello-world-bean-changed");
		
	}
	
	@GetMapping(path ="/hello/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
				return new HelloWorldBean(String.format("hellow World,%s",name));
		
	}
}
