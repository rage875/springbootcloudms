package com.restfulappsbuser.ms.mobileappws.userservice.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.userservice.UserService;

@Service
public class UserServiceImp implements UserService{
	private Map<String, UserRes> users;

	@Override
	public UserRes createUser(UserDetailsRequestModel userDetails) {
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

		return userRes;
	}
	
}
