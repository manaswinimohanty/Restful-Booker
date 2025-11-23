package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reports.ExtentManager;

public class LoggingFilters implements Filter {
private Logger logger= LogManager.getLogger(LoggingFilters.class);

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        System.out.println("inside overridden filter method");
        logRequest(filterableRequestSpecification);
        Response response=filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
        logResponse(response);
        return response;
    }
    private void logRequest(FilterableRequestSpecification reqSpec){
        System.out.println("============Filtering request==============");
        System.out.println("Request Uri: "+reqSpec.getURI());
        ExtentManager.logInfoDetails("Request Uri: "+reqSpec.getURI());
        ExtentManager.logInfoDetails("Headers are : ");
        Headers headers=reqSpec.getHeaders();
        ExtentManager.logHeaders(headers);
        ExtentManager.logInfoDetails("Request Body: ");
        if(reqSpec.getBody() ==null){
            ExtentManager.logInfoDetails("No Request body present");
        }else{
            ExtentManager.logJsonBody(reqSpec.getBody());
        }

        logger.info("Request Uri: "+reqSpec.getURI());
        logger.info("headers: "+reqSpec.getHeaders());
        logger.info("Body: "+reqSpec.getBody());
    }
    private void logResponse(Response response){

        System.out.println("============Filtering request==============");
        System.out.println("response status: " + response.getStatusCode());
        ExtentManager.logInfoDetails("response status: " + response.getStatusCode());
        ExtentManager.logInfoDetails("response headers: ");
        ExtentManager.logHeaders(response.getHeaders());
        ExtentManager.logInfoDetails("response body: ");
        if(response.getBody()==null) {
            ExtentManager.logInfoDetails("No Response body present");
        }
        else {
            ExtentManager.logJsonBody(response.getBody().prettyPrint());
        }

        logger.info("response status: "+response.getStatusCode());
        logger.info("response headers: "+response.getHeaders());
        logger.info("response body: "+response.getBody().prettyPrint());
    }
}
