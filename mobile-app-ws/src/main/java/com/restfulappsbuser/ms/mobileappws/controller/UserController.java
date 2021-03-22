package com.restfulappsbuser.ms.mobileappws.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.restfulappsbuser.ms.mobileappws.exceptions.UserServiceException;
import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UpdateUserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.userservice.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

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

		try {
			UserRes userRes = userService.getUser(id);

			return new ResponseEntity<>(
					userRes,
					HttpStatus.OK
					);
		}
		catch(Exception ex){
			throw new UserServiceException("A user service exception:" + ex.getLocalizedMessage());
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

		// Without dependency injection = new UserService().createUser(userDetails);
		UserRes userRes = userService.createUser(userDetails);

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

		UserRes userRes = userService.updateUser(id, userDetails);
		
		if(null != userRes)
		{
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

	@DeleteMapping(path="/{id}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					} )
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		if(null != userService.deleteUser(id))
		{
			return new ResponseEntity<>(
					HttpStatus.ACCEPTED
					);
		}
		else
		{
			return new ResponseEntity<>(
					HttpStatus.NO_CONTENT
					);
		}
	}

}
