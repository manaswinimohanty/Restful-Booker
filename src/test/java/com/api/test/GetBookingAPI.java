package com.api.test;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.api.models.Request.CreateBookingRequestPojo;
import com.api.models.Response.GetBookingResponsePojo;
import com.api.services.BookingService;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class GetBookingAPI {
	
	protected BookingService bookingService;
	protected Response r;
	public static int bookingid;
	private static String token; 
	
	@Test
	
	public void getBookingTest (ITestContext context) {
		
		bookingService=new BookingService();
		
		/*
		 * Faker faker=new Faker(); Map<String,Object>map=new HashMap<String,Object>();
		 * map.put("checkin", LocalDate.of(2024, 1,
		 * 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); map.put("checkout",
		 * LocalDate.of(2025, 1, 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		 * 
		 * CreateBookingRequestPojo
		 * createBookingRequestPojo=CreateBookingRequestPojo.builder().firstname(faker.
		 * name().firstName()).lastname(faker.name().lastName()) .bookingdates(map)
		 * .depositpaid(true).additionalneeds("breakfast").build();
		 * 
		 * 
		 * r=bookingService.createBooking(createBookingRequestPojo);
		 * 
		 * bookingid=r.then().statusCode(200).extract().path("bookingid");
		 */
		token=(String) context.getAttribute("token");
		
		bookingid=(int) context.getAttribute("bookingid");
		r=bookingService.getBooking(String.valueOf(bookingid));
		GetBookingResponsePojo getBookingResponsePojo=r.then().extract().as(GetBookingResponsePojo.class);
		
	//	System.out.println(pojoResponse.getFirstname());
	}
	
	

}
