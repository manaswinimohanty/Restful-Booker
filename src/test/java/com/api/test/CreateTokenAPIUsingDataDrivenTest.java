package com.api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.api.models.Request.CreateTokenPojo;
import com.api.models.Request.CreateTokenPojousingDDT;
import com.api.services.AuthService;

import io.restassured.response.Response;
import reports.ExtentManager;
import utils.ExcelUtils;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Listeners(listener.TestListener.class)
public class CreateTokenAPIUsingDataDrivenTest {
	
	@DataProvider(name="CreateTokenProvider")
	
	public Iterator<CreateTokenPojousingDDT> getTokenData() {
		List<CreateTokenPojousingDDT>CreateTokenPojoList=new ArrayList<>();
		List<LinkedHashMap<String,String>>tokenDataList=new ArrayList<>();
		tokenDataList=ExcelUtils.getExcelDataAsListofMap("CreateToken.xlsx","Sheet1");
		for(LinkedHashMap<String,String> data:tokenDataList) {
			
			
			CreateTokenPojousingDDT createTokenPojousingDDT=getCustomizedAirlineData(data);
			
			CreateTokenPojoList.add(createTokenPojousingDDT);
			
		}
		
		
		return CreateTokenPojoList.iterator();
		
	}
	
	
	
	
	@Test(dataProvider="CreateTokenProvider")
	public static void createTokenTest(CreateTokenPojousingDDT createTokenPojousingDDT) {
	//	Faker faker=new Faker();
		
	//	CreateTokenPojo pojo=new CreateTokenPojo.Builder().username("admin").password("password123").build();
		AuthService auth=new AuthService();
		//create customized data
		
		
		Response r=null;
		//r=auth.createToken(createTokenPojousingDDT);
		if(r.getStatusCode()==200) {
		String token=r.then().extract().path("token");
		if(token==null) {
			
			AssertJUnit.assertEquals(r.then().extract().path("reason"), "Bad credentials");
			ExtentManager.logPassDetails(createTokenPojousingDDT.getScenerioID());
		}
		else {
			ExtentManager.logInfoDetails(token);
			ExtentManager.logPassDetails(createTokenPojousingDDT.getScenerioID());
		}
		}
		//context.setAttribute("token", token);
		
	}
	
	
	
	public CreateTokenPojousingDDT getCustomizedAirlineData(LinkedHashMap<String,String>mapData) {
		CreateTokenPojousingDDT ddtToken=new CreateTokenPojousingDDT();
		ddtToken.setScenerioID(mapData.get("scenerioID"));
		ddtToken.setScenrioDesc(mapData.get("scenrioDesc"));
		ddtToken.setExpectedStatusCode(Integer.parseInt(mapData.get("expectedStatusCode")));
		
		
		
		if(!mapData.get("username").equals("NO_DATA"))
			ddtToken.setUsername(mapData.get("username"));
		if(!mapData.get("password").equals("NO_DATA"))
			ddtToken.setPassword(mapData.get("password"));
		
		return ddtToken;
		
		
	}
	 

}
