package factories;

import com.github.javafaker.Faker;
import models.requestModel.Bookingdates;
import models.requestModel.CreateBookingPojo;
import models.requestModel.CreateTokenPojo;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BookingFactories {
    public static Faker faker;

   static  {
       //System.out.println("inside BookingFactories static block");
      faker=new Faker();
    }

    public static CreateBookingPojo createBookingDefaultData(){

        Bookingdates bookingdates=Bookingdates.builder()
                .checkin(LocalDate.of(2024, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .checkout(LocalDate.of(2023, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        return CreateBookingPojo.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100,100000))
                .depositpaid(faker.random().nextBoolean())
                .bookingdates(bookingdates)
                .additionalneeds(faker.options().option("Breakfast","Lunch","Dinner")).build();
    }

    public static CreateTokenPojo createTokenDefaultData(){
        CreateTokenPojo createTokenPojo=CreateTokenPojo.builder().username("admin").password("password123").build();
        return createTokenPojo;
    }

}
