package feature2;

import Base.Base;
import Base.GetCall;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import units.feature1.apiCore.AddPlaceApi;
import units.feature1.request.AddPlaceReq;
import units.feature2.apiCore.GetPlaceApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class F21 extends Base implements GetCall {

    private HashMap<String, String> queryParams = new HashMap<String, String>();
    private String pathParams;

    public F21() throws IOException {
    }
    public void setQueryParams(){
        queryParams.put("key", "qaclick123");
        queryParams.put("place_id","1e441c9430eabd7b91eaa7f03c94e0d0");
    }

    @BeforeMethod
    public void beforeTest(){
        setQueryParams();
        setPathParams();
    }

    @Test
    private void f211() throws IOException {

        Response response =
                RestAssured.given()
                        .spec(Base.specBuilder())
                        .queryParam(String.valueOf(queryParams))
                        .when()
                        .get();
        System.out.println(response.prettyPrint());
        assertResponse(response);
    }

    public void setPathParams() {
        pathParams = GetPlaceApi.pathParam;
    }

    @Override
    public void afterTest() {

    }
}
