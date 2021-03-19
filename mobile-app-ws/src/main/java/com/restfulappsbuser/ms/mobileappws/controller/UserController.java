package com.restfulappsbuser.ms.mobileappws.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
