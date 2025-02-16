package com.api.test;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.api.models.Request.CreateBookingRequestPojo;
import com.api.models.Response.CreateBookingResponsePojo;
import com.api.services.BookingService;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class CreateBookingAPI {
	
	public static int bookingid;
	
	@Test
	public static void createBookingTest(ITestContext context ) {
		
		BookingService bookingService=new BookingService();
		
		Faker faker=new Faker();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("checkin", LocalDate.of(2024, 1, 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		map.put("checkout", LocalDate.of(2025, 1, 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		CreateBookingRequestPojo createBookingRequestPojo=CreateBookingRequestPojo.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
		.bookingdates(map)
		.depositpaid(true).additionalneeds("breakfast").build();
		
		
		Response r=bookingService.createBooking(createBookingRequestPojo);

		bookingid=r.then().statusCode(200).extract().path("bookingid");
		context.setAttribute("bookingid",bookingid );
		
		CreateBookingResponsePojo responsePojo =r.as(CreateBookingResponsePojo.class);
	//	System.out.println(responsePojo.getBooking().getBookingdates().get("checkin"));
		//assert.assertNotNull(bookingid);
		
		
	}

}
