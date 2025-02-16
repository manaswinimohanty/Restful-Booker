package com.api.services;

import com.api.filters.LoggingFilter;
import com.api.utils.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
	
	private RequestSpecification reqspec;
	
	private static String baseUri;
	
	static {
		
		String envType=System.getProperty("env")==null?"qa":System.getProperty("env");
		System.out.println(envType);
		try {
			baseUri=JsonUtils.getBaseUrl(System.getProperty("user.dir")+"/src/test/resources/environment/"+envType+"/BookingApiURL.json");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RestAssured.filters(new LoggingFilter());
	}
	
	
	
	
	public BaseService() {
		reqspec=RestAssured.given().baseUri(baseUri);
	}
	
	public Response postRequest(Object payload,String endpoint) {
		return reqspec.contentType(ContentType.JSON).body(payload).post(endpoint);
		
	}
	
	/*Invalid number of path parameters. Expected 0, was 1. Redundant path parameters are: id=1
	 * public Response getRequest(String endpoint) { return reqspec.log().all()
	 * //.pathParam("id", path) .contentType(ContentType.JSON).when().get(endpoint);
	 * 
	 * }
	 */
	
	public void setToken(String token) {
		reqspec.header("Cookie","token="+token);
		
	}
	
//	Basic YWRtaW46cGFzc3dvcmQxMjM=
	
	
	public Response getRequest(String endpoint) { 
		/*
		 * return reqspec.log().all() .pathParam("id", path)
		 * .contentType(ContentType.JSON).when().get(endpoint+"/{id}");
		 */
		
		return reqspec.contentType(ContentType.JSON).when().get(endpoint);
		
	}
	
	
	public Response putRequest(String endpoint,String path,Object payload) {
		return reqspec
				.pathParam("id", path) .contentType(ContentType.JSON).body(payload).when().put(endpoint+"/{id}");
			
	}
	

}
