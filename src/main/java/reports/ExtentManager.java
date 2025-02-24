package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import listener.TestListener;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentManager {
	
	public static ExtentReports extentReports;
	public static File ReportFileName;
	
	
	private ExtentManager() {}
	
	/*
	 * public static ExtentReports getInstance() { if(extentReports == null) {
	 * createInstance(); } return extentReports; }
	 * 
	 */
	
	public static ExtentReports createInstance() {
		String path=System.getProperty("user.dir")+"/ExtentReport/";
		ReportFileName=new File(path+getReportFileName());
		ExtentSparkReporter sparkReport=new ExtentSparkReporter(ReportFileName);
		System.out.println(path+getReportFileName());
		sparkReport.config().setReportName(getReportFileName());
		sparkReport.config().setDocumentTitle("Test Execution Report");
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setEncoding("UTF-8");
		sparkReport.config().setCss(".my_scroll_div{\r\n"
				+ "    overflow-y: auto;\r\n"
				+ "    max-height: 100px;\r\n"
				+ "}");
		extentReports=new ExtentReports();
		
		extentReports.attachReporter(sparkReport);
		
		return extentReports;
	}
	
	
	public static String getReportFileName() {

		String dateNtime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
		String reportName="TestReport"+dateNtime+".html";
		
		return reportName;
	}
	
	
	public static void logInfoDetails(String logs) {
		
		TestListener.extentTest.get().info(MarkupHelper.createLabel(logs, ExtentColor.GREY));
			  
	  }
	
	
	public static void logJsonBody(String log) {
		TestListener.extentTest.get().info(MarkupHelper.createCodeBlock(log, CodeLanguage.JSON));
	}
	
	public static void logXMLBody(String log) {
		TestListener.extentTest.get().info(MarkupHelper.createCodeBlock(log, CodeLanguage.XML));
	}
	
	
	public static void logHeaders(Headers headers) {
	//String[][]allHeaders=headers.asList().stream().map(header->new String[] {header.getName(),header.getValue()}).toArray(String[][]::new);
		List <Header> headerList=headers.asList();
		TestListener.extentTest.get().log(Status.INFO, MarkupHelper.createOrderedList(headerList));
		
	//	TestListener.extentTest.get().info(MarkupHelper.createTable(allHeaders,"table-sm"));
	
	
}
	public static void logFailureDetails(String log) {
		
		
		TestListener.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}
	
	
	public static void logExceptiom(String log) {
		
	TestListener.extentTest.get().fail(log);
	}
	
	public static void logPassDetails(String log) {
		TestListener.extentTest.get().log(Status.PASS,MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}
	
	
	
	
	
}