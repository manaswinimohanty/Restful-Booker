package APITest.BookingAPITest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonUtils.AssertionKeys;
import factories.BookingFactories;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateBookingPojo;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.bookingService.BookingService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@Listeners(TestListener.class)
public class GetBookingAPITest {
    //private BookingService bookingService;
    private Response response;
    ObjectMapper mapper;
    private static int bookingId;
    CreateBookingPojo createBookingPojo;



    private int createBooking(){
        BookingService bookingService=new BookingService();
        createBookingPojo=BookingFactories.createBookingDefaultData();
        response=bookingService.createBooking(createBookingPojo);
        bookingId=response.then().statusCode(200).body("any{it.key=='bookingid'}",is(true))
                .assertThat().body("bookingid",is(not("")))
                .extract().path("bookingid");
        return bookingId;
    }


    @Test
    private void getBookingAPITest(){
        BookingService bookingService=new BookingService();
        bookingId=createBooking();

        System.out.println("================"+bookingId);
        Response response=bookingService.getBooking(String.valueOf(bookingId));
        response.then().statusCode(200);
        mapper=new ObjectMapper();
        try {
            CreateBookingPojo recvBookingPojo=mapper.readValue(response.asString(), new TypeReference<CreateBookingPojo>() {});
            assertNverifyToken(createBookingPojo,recvBookingPojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public void assertNverifyToken(CreateBookingPojo sentData,Object recvData){
        if(sentData instanceof CreateBookingPojo && recvData instanceof CreateBookingPojo){
            List<AssertionKeys> assertionKeysList=new ArrayList<>();
            AssertionKeys assertionKeys=new AssertionKeys("JsonPath", "Expected_Value", "Actual_Value", "Result");
            assertionKeysList.add(assertionKeys);
            Class<?> CreateBookingPojoClass=sentData.getClass();
            Field[] fields=CreateBookingPojoClass.getDeclaredFields();

            /*for(Field field:fields){
                field.setAccessible(true);
                try {
                    System.out.println(field.getName() + " : " + field.get(sentData));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                }

            }*/

            if(sentData.equals(recvData)){
                System.out.println("sent data same as receive data");
            }
        }



    }
}
