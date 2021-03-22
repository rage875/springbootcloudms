package com.restfulappsbuser.ms.mobileappws.userservice.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UpdateUserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.shared.Utils;
import com.restfulappsbuser.ms.mobileappws.userservice.UserService;

@Service
public class UserServiceImp implements UserService{
	private Map<String, UserRes> users;
	private Utils utils;
	
	@Autowired
	public UserServiceImp(Utils utils)
	{
		this.utils = utils;
	}
	
	@Override
	public UserRes getUser(String id) {
		UserRes userRes = null;

		if(users.containsKey(id))
		{
			userRes = users.get(id);
		}
		
		return userRes;
	}

	@Override
	public UserRes createUser(UserDetailsRequestModel userDetails) {
		UserRes userRes = new UserRes();

		userRes.setFirstName(userDetails.getFirstName());
		userRes.setLastName(userDetails.getLastName());
		userRes.setEmail(userDetails.getEmail());
		
		String userId = utils.generateUserId();
		userRes.setId(userId);

		if(users == null) 
		{
			users = new HashMap<>();
		}
		users.put(userId, userRes);

		return userRes;
	}

	@Override
	public UserRes updateUser(String id, UpdateUserDetailsRequestModel userDetails) {
		UserRes userRes = null;

		if(users.containsKey(id))
		{
			userRes = users.get(id);

			userRes.setFirstName(userDetails.getFirstName());
			userRes.setLastName(userDetails.getLastName());

			users.put(id, userRes);
		}
		
		return userRes;
	}
	
	@Override
	public UserRes deleteUser(String id) {
		UserRes userRes = null;
		
		if(users.containsKey(id))
		{
			userRes = users.get(id);
			users.remove(id);
		}
		
		return userRes;
	}
	
}
