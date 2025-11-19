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

public Response postRequest(Object payload, String basePath){

        return reqSpec.contentType(ContentType.JSON).body(payload).when().post(basePath);
}

public Response getRequest(String basePath){
        return reqSpec.when().get(basePath);
}

public Response putRequest(Object payload,String basePath){
        return reqSpec.contentType(ContentType.JSON).accept(ContentType.JSON).header("Cookie","").body(payload).put(basePath);
}

}
