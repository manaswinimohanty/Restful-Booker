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

	public void logRequest(FilterableRequestSpecification requestSpec) {

		
		  ExtentManager.logInfoDetails("Request Uri: " + requestSpec.getURI());
		  ExtentManager.logInfoDetails("Headers are : ");
		  ExtentManager.logHeaders(requestSpec.getHeaders());
		  ExtentManager.logInfoDetails("Request Body: ");
		  ExtentManager.logJsonBody(requestSpec.getBody());
		 

		
		  logFilter.info("Request Uri: "+requestSpec.getURI());
		  logFilter.info("headers: "+requestSpec.getHeaders());
		  logFilter.info("Body: "+requestSpec.getBody());
		 

	}

	public void logResponse(Response response) {

		
		  ExtentManager.logInfoDetails("response status: " + response.getStatusCode());
		  ExtentManager.logInfoDetails("response headers: ");
		  ExtentManager.logHeaders(response.getHeaders());
		  ExtentManager.logInfoDetails("response body: ");
		  ExtentManager.logJsonBody(response.getBody().prettyPrint());
		 

		
		logFilter.info("response status: "+response.getStatusCode());
		logFilter.info("response headers: "+response.getHeaders());
		logFilter.info("response body: "+response.getBody().prettyPrint());
	}

}
