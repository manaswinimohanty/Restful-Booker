package APITest.base;

import commonUtils.JsonUtils;
import filters.LoggingFilters;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
    private RequestSpecification reqSpec;
    private static String baseUri;
    private static final String envFilePath = System.getProperty("user.dir")+"/src/test/resources/env/";

    static {
        System.out.println("inside BaseService static block");
        String envType = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            baseUri = JsonUtils.getBaseUri(envFilePath + envType + "/url.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        RestAssured.filters(new LoggingFilters());
    }

    public BaseService(){

        reqSpec=RestAssured.given().baseUri(baseUri);

    }

public Response postRequest(Object payload, String endPoint){

        return reqSpec.contentType(ContentType.JSON).body(payload).when().post(endPoint);
}

public Response getRequest(String endPoint){
        return reqSpec.when().get(endPoint);
}



public Response putRequest(Object payload,String endPoint){
        return reqSpec.contentType(ContentType.JSON)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").body(payload).when().put(endPoint);
}

public Response deleteRequest(String endPoint){
        return reqSpec.contentType(ContentType.JSON).header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").when().delete(endPoint);
}

}
