package com.api.test;

import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.api.models.Request.CreateBookingRequestPojo;
import com.api.models.Request.CreateTokenPojo;
import com.api.services.AuthService;
import com.api.services.BookingService;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class UpdateBookingAPI {
	
	BookingService bookingService;
	Response r;
	private static int bookingid;
	private static String token;
	
	@Test	
	public void updateBookingTest(ITestContext context) {
	
	//=====================generate token=================
	/*
	 * CreateTokenPojo pojo=new
	 * CreateTokenPojo.Builder().username("admin").password("password123").build();
	 * AuthService auth=new AuthService(); Response r=auth.CreateToken(pojo); String
	 * token=r.then().extract().path("token");
	 */
	
	//============generate booking id===============
	
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
	
	//====================update booking=================
		token=(String) context.getAttribute("token");
		bookingid=(int) context.getAttribute("bookingid");
		
		Faker faker=new Faker(); Map<String,Object>map=new HashMap<String,Object>();
		  map.put("checkin", LocalDate.of(2024,1,1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		  map.put("checkout",LocalDate.of(2025, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		  
		  CreateBookingRequestPojo
		  createBookingRequestPojo=CreateBookingRequestPojo.builder()
		  .firstname(faker.name().firstName())
		  .lastname(faker.name().lastName())
		  .totalprice(faker.number().numberBetween(100, 500))
		  .bookingdates(map)
		  .depositpaid(Stream.of(true,false).findAny().get())
		  .additionalneeds("breakfast").build();
	
		  bookingService=new BookingService();
		Response updateBookingResponse=bookingService.updateBooking(String.valueOf(bookingid), token,createBookingRequestPojo);
		
		
	}

}
