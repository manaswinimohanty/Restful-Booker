package services.authService;

import io.restassured.response.Response;
import APITest.base.BaseService;

public class AuthService extends BaseService {

    private static final String basePath="auth";


    public Response createToken(Object payload){
            return postRequest(payload,basePath);
    }
}
