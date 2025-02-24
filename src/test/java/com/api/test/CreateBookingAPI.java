package com.api.test;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.api.models.Request.CreateBookingRequestPojo;
import com.api.models.Response.CreateBookingResponsePojo;
import com.api.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import net.datafaker.Faker;
import utils.AssertionUtils;

public class CreateBookingAPI {
	
	public static int bookingid;
	private static ObjectMapper mapper;
	@Test
	public static void createBookingTest(ITestContext context ) {
		
		mapper=new ObjectMapper();
		BookingService bookingService=new BookingService();
		
		Faker faker=new Faker();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("checkin", LocalDate.of(2024, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		map.put("checkout", LocalDate.of(2025, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		CreateBookingRequestPojo createBookingRequestPojo=CreateBookingRequestPojo.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
		.bookingdates(map)
		.depositpaid(true).additionalneeds("breakfast").build();
		
		
		Response response=bookingService.createBooking(createBookingRequestPojo);
		if (response.statusCode() == 200) {

			bookingid=response.then().statusCode(200).extract().path("bookingid");
			context.setAttribute("bookingid",bookingid );
			try {
				Map<String,Object>receiveValue=response.jsonPath().getObject("booking",new TypeRef<Map<String,Object>>(){});
			Map<String,Object>sentValue=mapper.convertValue(createBookingRequestPojo, new TypeReference<Map<String,Object>>(){});
		//	AssertionUtils.assertNverify(response, expectedValueMap);
				AssertionUtils.assertNverify(receiveValue, sentValue);
				
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		}
		
		else
		{
			
		}
	
		
		
		
		
		
	}

}
