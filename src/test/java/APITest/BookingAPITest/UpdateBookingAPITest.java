package APITest.BookingAPITest;

import factories.BookingFactories;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.authService.AuthService;
import services.bookingService.BookingService;

import static org.hamcrest.Matchers.*;

//@Listeners(TestListener.class)
public class UpdateBookingAPITest {
    //private AuthService authService=new AuthService();
    private Response response;
    private BookingService bookingService=new BookingService();
    CreateBookingPojo createBookingPojo;
    private static int bookingId;


@Test(enabled = true)
    public void updateBooking(){
    bookingId=getBookingId();
    createBookingPojo=BookingFactories.createBookingDefaultData();
    response=bookingService.updateBooking(createBookingPojo,String.valueOf(bookingId));
    response.then().statusCode(200);
    System.out.println(response.asString());


    }
    private int getBookingId(){
        BookingService bookingService=new BookingService();
         createBookingPojo=BookingFactories.createBookingDefaultData();
        response=bookingService.createBooking(createBookingPojo);
        bookingId=response.then().statusCode(200).body("any{it.key=='bookingid'}",is(true))
                .assertThat().body("bookingid",is(not("")))
                .extract().path("bookingid");
        return bookingId;
    }


}
