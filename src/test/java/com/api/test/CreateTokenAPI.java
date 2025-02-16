package com.api.test;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.api.models.Request.CreateTokenPojo;
import com.api.services.AuthService;

import io.restassured.response.Response;

public class CreateTokenAPI {
	
	/*
	 * String payload="{\r\n" + "    \"username\" : \"admin\",\r\n" +
	 * "    \"password\" : \"password123\"\r\n" + "}";
	 */
	
	@Test
	public static void createTokenTest(ITestContext context) {
	//	Faker faker=new Faker();
		
		CreateTokenPojo pojo=new CreateTokenPojo.Builder().username("admin").password("password123").build();
		AuthService auth=new AuthService();
		Response r=auth.CreateToken(pojo);
		String token=r.then().extract().path("token");
		context.setAttribute("token", token);
		
	}
	 

}
