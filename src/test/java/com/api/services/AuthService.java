package com.api.services;

import com.api.models.Request.CreateTokenPojo;

import io.restassured.response.Response;

public class AuthService extends BaseService{
	
	private static final String basepath ="auth";
	
	public Response CreateToken(CreateTokenPojo authPayload) {
		return postRequest(authPayload,basepath);
	}
	
	

}
