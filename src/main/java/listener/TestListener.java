package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener{
public ExtentReports eReports;
public ExtentTest eTest;
public static ThreadLocal<ExtentTest>extentTestThreadLocal=new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        eTest=eReports.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(eTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

       extentTestThreadLocal.get().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName()+" passed. ", ExtentColor.GREEN));
        extentTestThreadLocal.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.logFailureDetails(result.getMethod().getMethodName());
        String stackTrace=result.getThrowable().getStackTrace().toString();
        stackTrace=stackTrace.replaceAll(",","<br>");
        String formattedMsg="<details>\n"+"<summary>Click here to see Exception logs</summary>\n"+
                " "+stackTrace+" \n"+"</details>\n";
        ExtentManager.logException(formattedMsg);
        extentTestThreadLocal.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().log(Status.SKIP,MarkupHelper.createLabel(result.getMethod().getMethodName()+" skipped"+result.getThrowable(),ExtentColor.AMBER));
    }

    @Override
    public void onStart(ITestContext context) {
        eReports= ExtentManager.createExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        if(eReports !=null){
            eReports.flush();
           File extentReportFile= ExtentManager.filePath;
           try{
               Desktop.getDesktop().browse(extentReportFile.toURI());
           } catch (IOException e) {
               throw new RuntimeException(e.getMessage());
           }

        }
    }
}
