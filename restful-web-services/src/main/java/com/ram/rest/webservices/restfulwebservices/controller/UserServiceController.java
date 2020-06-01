package com.ram.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ram.rest.webservices.restfulwebservices.bean.HelloWorldBean;
import com.ram.rest.webservices.restfulwebservices.user.User;
import com.ram.rest.webservices.restfulwebservices.user.UserDaoService;
import com.ram.rest.webservices.restfulwebservices.user.UserNotFoundException;

@RestController
public class UserServiceController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {

		return service.findAll();

	}

	@GetMapping("/users/{id}")
	public User getUserId(@PathVariable int id) {
		User user= service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id"+id);
		}
		return null;

	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserId(@PathVariable int id) {
	User user= service.deleteById(id);
	if(user==null) {
		throw new UserNotFoundException("id"+id);
	}
	

	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

}
