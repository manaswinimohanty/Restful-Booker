package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import reports.ExtentManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyMethodListener implements IInvokedMethodListener {
    ExtentReports extentReports = ExtentManager.createExtentReport();
    ExtentTest extentTest;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        System.out.println("before invocation of " + method.getTestMethod().getMethodName());
        extentTest = extentReports.createTest(method.getTestMethod().getMethodName());

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("after invocation " + method.getTestMethod().getMethodName());
        if (extentReports != null) {
            extentReports.flush();
            File extentReportFile = ExtentManager.filePath;
            try {
                Desktop.getDesktop().browse(extentReportFile.toURI());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}