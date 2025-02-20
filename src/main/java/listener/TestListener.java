package listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import reports.ExtentManager;

public class TestListener implements ITestListener{
	public static ExtentReports extentReports;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public static final Logger logger=LogManager.getLogger(TestListener.class);
	
	

	public void onTestStart(ITestResult result) {
		
		test = extentReports.createTest(result.getMethod().getMethodName());
		
		// Store the test object in the ThreadLocal object, mapping it to the unique thread ID
        extentTest.set(test);
        
        //logger.info("="+result.getMethod().getMethodName()+"started=");

	  }
	
	
	
	//public void onTestSuccess(ITestResult result) {
	//	extentTest.get().pass(MarkupHelper.createLabel(result.getName()+" Passed", ExtentColor.GREEN));


	//	logger.info(result.getMethod().getMethodName()+ "passed");
	//  }

	
	  public void onTestFailure(ITestResult result) {
		  
		  ExtentManager.logFailureDetails(result.getThrowable().getMessage());
		  String stackTrace=result.getThrowable().getStackTrace().toString();
		  stackTrace=stackTrace.replaceAll(",", "<br>");
		  String formmatedTrace = "<details>\n" +
	                "    <summary>Click Here To See Exception Logs</summary>\n" +
	                "    " + stackTrace + "\n" +
	                "</details>\n";
		  
		  

		     
		//  logger.info("="+result.getMethod().getMethodName()+" failed because of"+ result.getThrowable());
	  }

	  
	  public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP,MarkupHelper.createLabel(result.getMethod().getMethodName()+" skipped "+result.getThrowable(),ExtentColor.AMBER));
		
	//	   logger.error(result.getMethod().getMethodName()+" skipped");
	//	  logger.error(result.getThrowable());
	  }

	

	  
	  public void onStart(ITestContext context) {
		  extentReports=ExtentManager.createInstance();
	 
		  
		 //   logger.info(context.getName()+" started");//context test tag name from pom.xml
	  }

	  
	  public void onFinish(ITestContext context) {////context test tag name from pom.xml
		  if (extentReports != null)
	            extentReports.flush();
		  File reportFileName = ExtentManager.ReportFileName;
		
		try {
			Desktop.getDesktop().browse(reportFileName.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/*
			 * logger.info("Total pass: "+context.getPassedTests().size());
			 * logger.info("Total Fail: "+context.getFailedTests().size());
			 * logger.info("Total skipped: "+context.getSkippedTests().size());
			 */
		  
	  }
	 
	
	
	
	
}
