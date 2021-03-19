package com.restfulappsbuser.ms.mobileappws.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	// Defining basic CRUD operations
	@GetMapping(path="/{id}")
	public String getUser(@PathVariable String id) {
		return String.format("Get user was called %s", id);
	}
	
	@PostMapping
	public String createUser() {
		return "Create user was called";
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
