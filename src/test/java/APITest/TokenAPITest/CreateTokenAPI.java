package APITest.TokenAPITest;

import factories.BookingFactories;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.authService.AuthService;

import static org.hamcrest.Matchers.*;

//@Listeners(TestListener.class)
public class CreateTokenAPI {
    private AuthService authService=new AuthService();
    private Response response;

    @Test(description = "generate token")
    public void createTokenAPITest() {
        try {
            response = authService.createToken(BookingFactories.createTokenDefaultData());
            response.then().statusCode(200).body("any{it.key == 'token'}", is(true)).assertThat().body("token", is(not("")),"token",is(notNullValue()));
        } catch (Exception e) {
           // ExtentManager.logException(e.getMessage());
            System.out.println(e.getMessage());
        }
    }




}
