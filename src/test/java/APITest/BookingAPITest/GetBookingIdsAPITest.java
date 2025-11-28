package APITest.BookingAPITest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import commonUtils.AssertUtils;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.ExtentManager;
import services.bookingService.BookingService;

import java.util.List;


public class GetBookingIdsAPITest {
    private BookingService bookingService=new BookingService();
    Response response;

    @Test(enabled = true,alwaysRun = true, description="get all booking Ids")
    public void getAllIds(){
        response=bookingService.getBookingIds();
        response.then().statusCode(200);
        List<Integer> bookingIds=response.jsonPath().getList("bookingid");
        System.out.println(bookingIds.toString());
        if(AssertUtils.validateIntegerArray(bookingIds)){
            TestListener.extentTestThreadLocal.get().log(Status.INFO, MarkupHelper.createLabel("Assertion successful", ExtentColor.GREEN));
        }
        else{
            ExtentManager.logException("Assertion failed");
        }
    }

    @Test(enabled = false, description="Filter by name")
    public void getBookingIdsFilterByName(ITestContext testContext){
        CreateBookingPojo bookingPojo= (CreateBookingPojo) testContext.getAttribute("user");
        bookingService.getBookingIdsByName(bookingPojo.getFirstname(),bookingPojo.getLastname());

    }

    @Test(enabled = false, description="Filter by Date")
    public void getBookingIdsFilterByDate(ITestContext testContext){
        CreateBookingPojo bookingPojo= (CreateBookingPojo) testContext.getAttribute("user");
       bookingService.getBookingIdsByDate(bookingPojo.getFirstname(),bookingPojo.getBookingdates().getCheckout());

    }
}
