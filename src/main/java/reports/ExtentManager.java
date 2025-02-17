package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
	
	public static ExtentReports extentReports;
	
	private ExtentManager() {}
	
	/*
	 * public static ExtentReports getInstance() { if(extentReports == null) {
	 * createInstance(); } return extentReports; }
	 * 
	 */
	
	public static ExtentReports createInstance() {
		
		ExtentSparkReporter sparkReport=
			new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/test-output/ExtentReport/"));
		sparkReport.config().setReportName(getReportFileName());
		sparkReport.config().setDocumentTitle("Test Execution Report");
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setEncoding("UTF-8");
		extentReports=new ExtentReports();
		extentReports.attachReporter(sparkReport);
		
		return extentReports;
	}
	
	
	public static String getReportFileName() {
		String dateNtime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String reportName="Test Report"+dateNtime+".html";
		return reportName;
	}

}
