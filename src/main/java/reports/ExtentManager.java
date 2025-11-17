package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import listener.TestListener;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentManager {
public static ExtentReports extentReports;
    private static final String extentFilePath = System.getProperty("user.dir") + "/ExtentReport/";
    public static File filePath;
    private ExtentManager() {
    }

    public static ExtentReports createExtentReport() {
        String path = extentFilePath + getReportName();
        filePath=new File(path);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        sparkReporter.config().setReportName(getReportName());
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("UTF-8");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        return extentReports;

    }

    private static String getReportName(){
        String dateNtime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        String reportName="TestReport"+dateNtime+".html";
        return reportName;
    }

    public static void logInfoDetails(String logs){
        TestListener.extentTestThreadLocal.get().info(MarkupHelper.createLabel(logs, ExtentColor.GREY));
    }

    public static void logJsonBody(String logs){
        TestListener.extentTestThreadLocal.get().info(MarkupHelper.createCodeBlock(logs, CodeLanguage.JSON));
    }

    public static void logXmlBody(String logs){
        TestListener.extentTestThreadLocal.get().info(MarkupHelper.createCodeBlock(logs, CodeLanguage.XML));
    }

    public static void logHeaders(Headers allHeaders){
        List<Header> headerList=allHeaders.asList();
        TestListener.extentTestThreadLocal.get().info(MarkupHelper.createOrderedList(headerList));

    }

    public static void logFailureDetails(String logs){
        TestListener.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(logs+ " failed",ExtentColor.RED));
    }

    public static void logException(String logs){
        TestListener.extentTestThreadLocal.get().fail(logs);

    }


}
