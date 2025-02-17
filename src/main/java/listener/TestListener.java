package listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class TestListener implements ITestListener{
	public static ExtentReports extentReports;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public static final Logger logger=LogManager.getLogger(TestListener.class);
	
	

	public void onTestStart(ITestResult result) {
		// Create a new test in the ExtentReports and assign it to the test variable
		
		test = extentReports.createTest(result.getMethod().getMethodName());
		
		// Store the test object in the ThreadLocal object, mapping it to the unique thread ID
        extentTest.set(test);
		
		//logger.info("="+result.getMethod().getMethodName()+" started=");
	  }
	
	
	
	public void onTestSuccess(ITestResult result) {
		
	    extentTest.get().log(Status.PASS, "test passed");

		//logger.info("="+result.getMethod().getMethodName()+" passed=");
	  }

	
	  public void onTestFailure(ITestResult result) {
		  
		  extentTest.get().log(Status.FAIL, result.getMethod().getMethodName()+" failed because of"+ result.getThrowable());
		     
		//  logger.info("="+result.getMethod().getMethodName()+" failed because of"+ result.getThrowable()+"==");
	  }

	  
	  public void onTestSkipped(ITestResult result) {
		  extentTest.get().log(Status.SKIP,result.getMethod().getMethodName()+" skipped "+result.getThrowable());
		//  logger.error(result.getMethod().getMethodName()+" skipped");
		//  logger.error(result.getThrowable());
	  }

	

	  
	  public void onStart(ITestContext context) {
		  extentReports=ExtentManager.createInstance();
	 //  logger.info(context.getName()+" started");//context test tag name from pom.xml
	  }

	  
	  public void onFinish(ITestContext context) {////context test tag name from pom.xml
		  if (extentReports != null)
	            extentReports.flush();
		//  logger.info("Test Suite completed");
	  }
	
	
	
	
}
