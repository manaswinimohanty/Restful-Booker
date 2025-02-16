package com.api.test;

import org.testng.annotations.Test;

import com.api.models.Request.CreateBookingRequestPojo;
import com.api.services.BookingService;

import io.restassured.response.Response;

public class GetBookingIdsAPI {
	
	
	
	@Test
	
	public void getBookingIdTest() {
		
		BookingService bookingService=new BookingService();
		Response r=bookingService.getBookingID();
	
	//	System.out.println(pojoResponse.getFirstname());
	}
	
	

}
