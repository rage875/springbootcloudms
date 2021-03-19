package com.restfulappsbuser.ms.mobileappws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfulappsbuser.ms.mobileappws.model.reponse.User;

@RestController
@RequestMapping("users")
public class UserController {

	// Defining basic CRUD operations
	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false) String sort) {
		return String.format("Get users was called: page:%d, limit:%d, sort:%s", page, limit, sort);
	}

	@GetMapping(path="/{id}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					} )
	public ResponseEntity<User> getUser(@PathVariable String id) {
		User user = new User();

		user.setFirstName("first");
		user.setLastName("last");
		user.setEmail("@test");

		return new ResponseEntity<User>(
				user,
				HttpStatus.OK
				);
	}

	@PostMapping
	public ResponseEntity<String> createUser() {
		return new ResponseEntity<String>(
				"Create user was called",
				HttpStatus.CREATED
				);
	}
	
	@PutMapping
	public String updateUser(@PathVariable String id) {
		return "Update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "Delete user was called";
	}
}
