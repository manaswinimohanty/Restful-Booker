package APITest.BookingAPITest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonUtils.AssertUtils;
import commonUtils.CommonData;
import factories.BookingFactories;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.bookingService.BookingService;

import java.util.Map;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

//@Listeners(TestListener.class)
public class CreateBookingAPITest {

    private Response response;
    private ObjectMapper objectMapper=new ObjectMapper();
    private BookingService bookingService=new BookingService();

    @Test(enabled = true)
    public void createBookingAPITest(ITestContext testContext){

        CreateBookingPojo payload=BookingFactories.createBookingDefaultData();
       // CommonData.setUsers(payload);
        response=bookingService.createBooking(payload);
        response.then().statusCode(200).body("$",hasKey("bookingid"));
        CommonData.setBookingId(response.path("bookingid"));
        //testContext.setAttribute("bookingid",response.path("bookingid"));
       // testContext.setAttribute("user",payload);

        try {
            Map<String, Object> receiveData = response.jsonPath().getObject("booking", new TypeRef<Map<String, Object>>() {
            });
            Map<String, Object> sentData = objectMapper.convertValue(payload, new TypeReference<Map<String, Object>>() {
            });
        AssertUtils.assertNVerify(sentData,receiveData);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       // CommonData.clearCurrentUser();
      //  CommonData.clearCurrentBookingIds();
    }




}
