package models.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class Bookingdates {
    private String checkin;
    private String checkout;


}
