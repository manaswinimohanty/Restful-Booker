package com.api.models.Request;

public class CreateTokenPojo {
	
	private String username;
	private String password;
	
	public CreateTokenPojo(String username,String password) {
		this.username=username;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
public static class Builder{
	
	private String username;
	private String password;
	
	public Builder username(String username) {
		this.username= username;
		return this;
	}
		
	public Builder password(String password) {
		this.password= password;
		return this;
	}
	
	public CreateTokenPojo build() {
		
		CreateTokenPojo createTokenPojo=new CreateTokenPojo(username,password);
		
		return createTokenPojo;
		
		
	}
	
}

}