package APITest.TokenAPITest;

import commonUtils.ExcelUtils;
import io.restassured.response.Response;
import listener.TestListener;
import models.requestModel.CreateTokenPojoUsingDDT;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.authService.AuthService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

@Listeners(TestListener.class)
public class CreateTokenAPITestUsingDDT {

    private AuthService authService=new AuthService();
    private Response response;
    private static final String excelFile=System.getProperty("user.dir")+"/src/test/resources/CreateToken.xlsx";
    private static final String sheetName="Sheet1";

@DataProvider(name="Create Token")
private Iterator<CreateTokenPojoUsingDDT> getExcelData(){
    List<LinkedHashMap<String,String>>excelData;
    List<CreateTokenPojoUsingDDT>createTokenPojoUsingDDTList=new ArrayList<>();
    excelData=ExcelUtils.readExcelData(excelFile,sheetName);
    for(LinkedHashMap<String,String>eachMap:excelData){
        CreateTokenPojoUsingDDT pojoData=getCustomizedData(eachMap);
        createTokenPojoUsingDDTList.add(pojoData);
    }
    return createTokenPojoUsingDDTList.iterator();

}


private  CreateTokenPojoUsingDDT getCustomizedData(LinkedHashMap<String,String>mapData){
    CreateTokenPojoUsingDDT createTokenPojoUsingDDT=new CreateTokenPojoUsingDDT();
    createTokenPojoUsingDDT.setScenerioID(mapData.get("ScenerioID"));
    createTokenPojoUsingDDT.setScenrioDesc(mapData.get("ScenrioDesc"));
    createTokenPojoUsingDDT.setExpectedStatusCode(Integer.parseInt(mapData.get("Expected StatusCode")));
    createTokenPojoUsingDDT.setExpectedErrorMessage(mapData.get("Expected ErrorMessage"));
    if(!mapData.get("Username").equalsIgnoreCase("NO_DATA"))
    createTokenPojoUsingDDT.setUsername(mapData.get("Username"));
    if(!mapData.get("Password").equalsIgnoreCase("NO_DATA"))
    createTokenPojoUsingDDT.setPassword(mapData.get("Password"));
    return createTokenPojoUsingDDT;

}


    @Test(description = "generate token",dataProvider = "Create Token")
    public void createTokenAPITestUsingDDT(CreateTokenPojoUsingDDT createTokenPojoUsingDDT){
    if(createTokenPojoUsingDDT.getScenerioID().equalsIgnoreCase("CreateToken_HappyPath")) {
        response = authService.createToken(createTokenPojoUsingDDT);
        response.then().statusCode(createTokenPojoUsingDDT.getExpectedStatusCode())
                .body("$",hasKey("token"))
                .assertThat().body("token",is(not(""))).and()
                .body("token",is(notNullValue()));
    }
    else{
        response = authService.createToken(createTokenPojoUsingDDT);
        response.then().statusCode(createTokenPojoUsingDDT.getExpectedStatusCode())
                .body("$",hasKey("reason"))
                .assertThat().body("reason",equalToIgnoringCase("Bad credentials"));
    }

    }



}
