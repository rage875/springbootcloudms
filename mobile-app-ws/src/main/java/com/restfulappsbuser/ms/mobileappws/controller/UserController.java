package com.restfulappsbuser.ms.mobileappws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UpdateUserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users")
public class UserController {
	private Map<String, UserRes> users;

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
	public ResponseEntity<UserRes> getUser(@PathVariable String id) {
	
		if(users.containsKey(id))
		{
			return new ResponseEntity<>(
					users.get(id),
					HttpStatus.OK
					);
		}
		else
		{
			return new ResponseEntity<>(
					HttpStatus.NO_CONTENT
					);
		}
	}

	@PostMapping(consumes = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE},
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRes> createUser(
			@Valid
			@RequestBody UserDetailsRequestModel userDetails) {
		UserRes userRes = new UserRes();

		userRes.setFirstName(userDetails.getFirstName());
		userRes.setLastName(userDetails.getLastName());
		userRes.setEmail(userDetails.getEmail());

		String userId = UUID.randomUUID().toString();
		userRes.setId(userId);

		if(users == null) 
		{
			users = new HashMap<>();
		}
		users.put(userId, userRes);

		return new ResponseEntity<UserRes>(
				userRes,
				HttpStatus.CREATED
				);
	}

	@PutMapping(path="/{id}", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
	produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRes> updateUser(@PathVariable String id,
			@Valid
			@RequestBody UpdateUserDetailsRequestModel userDetails) {

		if(users.containsKey(id))
		{
			UserRes userRes = users.get(id);

			userRes.setFirstName(userDetails.getFirstName());
			userRes.setLastName(userDetails.getLastName());

			users.put(id, userRes);

			return new ResponseEntity<>(
					userRes,
					HttpStatus.OK
					);
		}
		else
		{
			return new ResponseEntity<>(
					HttpStatus.NO_CONTENT
					);
		}
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete user was called";
	}

}
