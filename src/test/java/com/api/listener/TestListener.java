package com.api.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	
	public static final Logger logger=LogManager.getLogger(TestListener.class);

	public void onTestStart(ITestResult result) {
		
		logger.info("======================="+result.getMethod().getMethodName()+" started=====================");
	  }
	
	public void onTestSuccess(ITestResult result) {

		logger.info("=============="+result.getMethod().getMethodName()+" passed===========");
	  }

	
	  public void onTestFailure(ITestResult result) {
		  logger.info("=============="+result.getMethod().getMethodName()+" failed because of"+ result.getThrowable()+"=========");
	  }

	  
	  public void onTestSkipped(ITestResult result) {
		  logger.error(result.getMethod().getMethodName()+" skipped");
		  logger.error(result.getThrowable());
	  }

	

	  
	  public void onStart(ITestContext context) {
	   
	   logger.info(context.getName()+" started");//context test tag name from pom.xml
	  }

	  
	  public void onFinish(ITestContext context) {////context test tag name from pom.xml
		  logger.info("Test Suite completed");
	  }
	
	
	
	
}
