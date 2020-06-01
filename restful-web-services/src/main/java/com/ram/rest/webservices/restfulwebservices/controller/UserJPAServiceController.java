package com.ram.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import java.util.Optional;
//import org.springframework.hateoas.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ram.rest.webservices.restfulwebservices.user.Post;
import com.ram.rest.webservices.restfulwebservices.user.PostRepository;
import com.ram.rest.webservices.restfulwebservices.user.User;
import com.ram.rest.webservices.restfulwebservices.user.UserDaoService;
import com.ram.rest.webservices.restfulwebservices.user.UserNotFoundException;
import com.ram.rest.webservices.restfulwebservices.user.UserRepository;

@RestController
public class UserJPAServiceController {

	@Autowired
	private UserDaoService service;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {

		return userRepository.findAll();

	}

/*	@GetMapping("/jpa/users/{id}")
	public User getUserId(@PathVariable int id) {
		Optional<User>  user= userRepository.findById(id);
		if(user==null) {
			throw new UserNotFoundException("id"+id);
		}
		return null;

	}*/
	
	
/*	@GetMapping("/jpa/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id){
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) 
			throw  new UserNotFoundException("id"+id);
		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}*/
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserId(@PathVariable int id) {
	 userRepository.deleteById(id);
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveAllUsers(@PathVariable int id){
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id"+id);
		}
		return userOptional.get().getPosts();
		
		
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {

		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id"+id);
		}
		User user=userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		
		
		//return userOptional.get().getPosts();
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
}
