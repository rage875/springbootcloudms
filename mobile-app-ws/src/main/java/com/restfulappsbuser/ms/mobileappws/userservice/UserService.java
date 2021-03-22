package com.restfulappsbuser.ms.mobileappws.userservice;

import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UpdateUserDetailsRequestModel;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;

public interface UserService {
	public UserRes getUser(String id);
	public UserRes createUser(UserDetailsRequestModel userDetails);
	public UserRes updateUser(String id, UpdateUserDetailsRequestModel userDetails);
	public UserRes deleteUser(String id);
}
