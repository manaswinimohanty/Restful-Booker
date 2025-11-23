package services.bookingService;

import io.restassured.response.Response;
import APITest.base.BaseService;

/**
 * This class will contain all booking api services like create Booking, get booking, update booking N delete Booking
 * This class will also provide basepath for booking related service
 * each booking related api will pass either payload or content Type to corresponding service method
 */

public class BookingService extends BaseService {
private static final String basePath="booking";

    public Response createBooking(Object payload){
        return postRequest(payload,basePath);
    }

    public Response getBookingIds(){
        return getRequest(basePath);
    }


    public Response getBookingIdsByName(String firstName,String lastName )
    {

        return getRequest(basePath+"?firstname="+firstName+"&lastname="+lastName);
    }

    public Response getBookingIdsByDate(String checkin,String checkout ){
        return getRequest(basePath+"?checkin="+checkin+"&checkout="+checkout);
    }

    public Response getBooking(String bookingId){
         return getRequest(basePath+"/"+bookingId);
    }

    public Response updateBooking(Object payload,String bookingId){
        return putRequest(payload,basePath+"/"+bookingId);
    }

    public Response deleteBooking(String bookingId){
        return deleteRequest(basePath+"/"+bookingId);

    }



}
