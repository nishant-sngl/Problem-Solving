package feature1;

import Base.Base;
import Base.PostCall;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import units.feature1.apiCore.AddPlaceApi;
import units.feature1.request.AddPlaceReq;
import units.feature1.request.Location;
import units.feature1.response.AddPlaceResp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class F11 extends Base implements PostCall {

    private AddPlaceApi addPlaceApi = new AddPlaceApi();
    private AddPlaceReq addPlaceReq;
    private HashMap<String, String> queryParams = new HashMap<String, String>();
    private String pathParams;

    public F11() throws IOException {
    }

    public void setQueryParams(){
        queryParams.put("key", "qaclick123");
    }

    public void setPathParams() {
        pathParams = AddPlaceApi.pathParams;
    }

    public void setPayload(){
        addPlaceReq = addPlaceApi.getPayload();
        addPlaceReq.setName("name1");
        addPlaceReq.setPhone_number("(+91) 983 893 3937");
        addPlaceReq.setAddress("address1");
        addPlaceReq.setLanguage("French-IN");

        ArrayList<String> types = new ArrayList<String>();
        types.add("shoe par");
        types.add("shop");
        addPlaceReq.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlaceReq.setLocation(location);
    }

    @BeforeMethod
    public void beforeTest(){
        setPayload();
        setQueryParams();
        setPathParams();
    }

    @Test(groups = {"g1"})
    private void test() throws IOException {
        Response response =
                RestAssured.given()
                .spec(Base.specBuilder())
                .queryParam(String.valueOf(queryParams))
                .body(addPlaceReq)
                .when()
                .post(pathParams);
        System.out.println(response.prettyPrint());
        assertResponse(response);

        AddPlaceResp addPlaceResp = response.getBody().as(AddPlaceResp.class);
        System.out.println(addPlaceResp.getPlace_id());

    }

    @AfterMethod
    public void afterTest(){
    }
}
