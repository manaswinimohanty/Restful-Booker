package APITest.BookingAPITest;

import factories.BookingFactories;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.bookingService.BookingService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

//@Listeners(TestListener.class)
public class DeleteBookingAPITest {
BookingService bookingService=new BookingService();
private Response response;
private int bookingId;


@Test(enabled = true)
    public void deleteBooking(){
    //bookingId =getBookingId();
    response=bookingService.deleteBooking(String.valueOf(getBookingId()));
    response.then().statusCode(201);


}
    private int getBookingId(){
        BookingService bookingService=new BookingService();
        CreateBookingPojo createBookingPojo= BookingFactories.createBookingDefaultData();
        response=bookingService.createBooking(createBookingPojo);
        bookingId=response.then().statusCode(200).body("any{it.key=='bookingid'}",is(true))
                .assertThat().body("bookingid",is(not("")))
                .extract().path("bookingid");
        return bookingId;
    }

}
