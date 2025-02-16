package com.api.models.Response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder=true)
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class GetBookingResponsePojo {
	
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private Map<String,Object>bookingdates;
	
	private String additionalneeds;
		
	

}
