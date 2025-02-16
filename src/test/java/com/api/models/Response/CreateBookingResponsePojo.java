package com.api.models.Response;

import com.api.models.Request.CreateBookingRequestPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)

public class CreateBookingResponsePojo {
	
	private int bookingid;
	
	private CreateBookingRequestPojo booking;
	
	

}
