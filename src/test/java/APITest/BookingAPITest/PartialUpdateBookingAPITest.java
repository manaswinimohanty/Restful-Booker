package APITest.BookingAPITest;

import factories.BookingFactories;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.bookingService.BookingService;

import static factories.BookingFactories.faker;
import static org.hamcrest.Matchers.*;

//@Listeners(TestListener.class)
public class PartialUpdateBookingAPITest {
    private CreateBookingPojo createBookingPojo;
    //private BookingService bookingService=new BookingService();
    private final BookingService bookingService = new BookingService();
    private int bookingId;


@Test(enabled = true,alwaysRun = true)
public void partialUpdateBooking(){
    bookingId=getBookingId();
    //createBookingPojo=createBookingPojo.toBuilder().firstname(faker.name().firstName()).lastname(faker.name().lastName()).build();
    String body="{\n" +
            "    \"firstname\" : \"James\",\n" +
            "    \"lastname\" : \"Brown\"\n" +
            "}";
    Response response=bookingService.partialBookingUpdate(body,String.valueOf(bookingId));
    response.then().assertThat().statusCode(200);

}
    private int getBookingId(){

        createBookingPojo= BookingFactories.createBookingDefaultData();
        Response res=bookingService.createBooking(createBookingPojo);
        bookingId=res.then().statusCode(200).body("$",hasKey("bookingid"))
                .assertThat().body("bookingid",notNullValue())
                .extract().path("bookingid");
        return bookingId;
    }
}
