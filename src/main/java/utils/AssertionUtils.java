package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import listener.TestListener;
import reports.ExtentManager;

public class AssertionUtils {
	
	public static void assertNverify(Map<String,Object>actual,Map<String,Object>expected) {
		List<AssertionKeys> actualValueList=new ArrayList<>();
		AssertionKeys assertKeys=new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT");
		actualValueList.add(assertKeys);
		boolean isMatched=false;
		
		Set<String>allKeys =expected.keySet();
		for(String eachKey:allKeys) {
			Optional<Object>optionalValue=Optional.ofNullable(actual.get(eachKey));
			if(optionalValue.isPresent()) {
				Object actualValue=actual.get(eachKey);
				if(actualValue.equals(expected.get(eachKey))) {
					actualValueList.add(new AssertionKeys(eachKey,expected.get(eachKey),actualValue,"Matched"));
					isMatched=true;
				}
				else {
					actualValueList.add(new AssertionKeys(eachKey,expected.get(eachKey),actualValue,"Not Matched"));
				}
			}
		}
		
		if(isMatched) {
			ExtentManager.logPassDetails("all assertions matched");

		}
		else {
			
			String[][]finalAssertionsMap=actualValueList.stream()
					.map(each -> new String[]{each.getJsonPath(),String.valueOf(each.getExpectedValue())
							,String.valueOf(each.getActualValue()),each.getResult()}).toArray(String[][]::new);
			
			TestListener.extentTest.get().fail(MarkupHelper.createTable(finalAssertionsMap, "table-sm"));
			
		}
		
		
		
	}
	
	/*public static void assertNverify(Response response,Map<String,Object>expectValueMap) {
		List<AssertionKeys> actualValueList=new ArrayList<>();
		AssertionKeys assertKeys=new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT");
		actualValueList.add(assertKeys);
		boolean allMatched = false;
		
		Set<String>allKeys =expectValueMap.keySet();
		
		for(String eachKey:allKeys) {
			//Optional<Object>optionalValue=Optional.ofNullable(response.jsonPath().get(eachKey));
			
			
			if(optionalValue.isPresent()) {
				Object actualValue=optionalValue.get();
				if(actualValue.equals(expectValueMap.get(eachKey))) {
				actualValueList.add(new AssertionKeys(eachKey,expectValueMap.get(eachKey),actualValue,"Matched"));
				allMatched=true;
				}
				else {
					actualValueList.add(new AssertionKeys(eachKey,expectValueMap.get(eachKey),actualValue,"Not Matched"));
				}
			}
				
			
			
		}
		if(allMatched) {
			ExtentManager.logPassDetails("all assertions matched");
		}
		else {
			ExtentManager.logFailureDetails("all assertions did not match");
			
			String[][]finalAssertionsMap=actualValueList.stream()
			.map(each -> new String[]{each.getJsonPath(),String.valueOf(each.getExpectedValue())
					,String.valueOf(each.getActualValue()),each.getResult()}).toArray(String[][]::new);
			
		TestListener.extentTest.get().log(Status.INFO,MarkupHelper.createTable(finalAssertionsMap,"table-sm"));
			
			String[][] array=actualValueList.stream()
            .map(assertions->new String[]{assertions.getJsonPath(),String.valueOf(assertions.getExpectedValue())
            		,String.valueOf(assertions.getActualValue()),assertions.getResult()}).toArray(String[][]::new);	
			
			
			
			
		} //else
			
	}*/
		
			
		}
