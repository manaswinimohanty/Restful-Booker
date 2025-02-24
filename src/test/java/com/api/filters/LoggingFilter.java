package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import reports.ExtentManager;

public class LoggingFilter implements Filter {

	private static final Logger logFilter = LogManager.getLogger(LoggingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		logRequest(requestSpec);

		Response response = ctx.next(requestSpec, responseSpec);
		logResponse(response);
		return response;
	}

	public void logRequest(FilterableRequestSpecification reqSpec) {

		
		  ExtentManager.logInfoDetails("Request Uri: " + reqSpec.getURI());
		  ExtentManager.logInfoDetails("Headers are : ");
		  ExtentManager.logHeaders(reqSpec.getHeaders());
		  ExtentManager.logInfoDetails("Request Body: ");
		  if(reqSpec.getBody()==null) {
			  ExtentManager.logInfoDetails("No Request body present");
		  }
		  else {
			  ExtentManager.logJsonBody(reqSpec.getBody());
		  }
		 

		
		  logFilter.info("Request Uri: "+reqSpec.getURI());
		  logFilter.info("headers: "+reqSpec.getHeaders());
		  logFilter.info("Body: "+reqSpec.getBody());
		 

	}

	public void logResponse(Response response) {

		
		  ExtentManager.logInfoDetails("response status: " + response.getStatusCode());
		  ExtentManager.logInfoDetails("response headers: ");
		  ExtentManager.logHeaders(response.getHeaders());
		  ExtentManager.logInfoDetails("response body: ");
		  if(response.getBody()==null) {
			  ExtentManager.logInfoDetails("No Request body present");
		  }
		  else {
			  ExtentManager.logJsonBody(response.getBody().prettyPrint());
		  }
		 

		
		logFilter.info("response status: "+response.getStatusCode());
		logFilter.info("response headers: "+response.getHeaders());
		logFilter.info("response body: "+response.getBody().prettyPrint());
	}

}
