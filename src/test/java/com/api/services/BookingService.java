package com.api.services;

import com.api.models.Request.CreateBookingRequestPojo;

import io.restassured.response.Response;

public class BookingService extends BaseService{
	
	private static final String basepath="booking";
	
	public Response createBooking(CreateBookingRequestPojo payload) {
		return postRequest(payload,basepath);
		
	}
	
	/*
	 * public Response getBooking(String path) { return
	 * getRequest(basepath+"/"+path);
	 * 
	 * }
	 */
	
	public Response getBooking(String path) {
		return getRequest(basepath+"/"+path);
	}
	
	public Response getBookingID() {
		return getRequest(basepath);
	}
	
	public Response updateBooking(String path,String token,CreateBookingRequestPojo payload) {
		setToken(token);
		return putRequest(basepath,path,payload);
	}
	
	

}
