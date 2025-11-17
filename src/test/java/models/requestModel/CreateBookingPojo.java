package models.requestModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class CreateBookingPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreateBookingPojo pojo = (CreateBookingPojo) obj;
        return totalprice == pojo.totalprice && depositpaid == pojo.depositpaid && Objects.equals(firstname, pojo.firstname) && Objects.equals(lastname, pojo.lastname) && Objects.equals(bookingdates, pojo.bookingdates) && Objects.equals(additionalneeds, pojo.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }
}
