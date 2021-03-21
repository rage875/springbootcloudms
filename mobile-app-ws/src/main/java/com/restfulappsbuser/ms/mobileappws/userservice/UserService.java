package com.restfulappsbuser.ms.mobileappws.userservice;

import com.restfulappsbuser.ms.mobileappws.model.reponse.UserRes;
import com.restfulappsbuser.ms.mobileappws.model.request.UserDetailsRequestModel;

public interface UserService {
	public UserRes createUser(UserDetailsRequestModel userDetails);
}
