package units.feature1.apiCore;

import io.restassured.response.Response;
import units.feature1.request.AddPlaceReq;
import units.feature1.response.AddPlaceResp;

import java.util.HashMap;
import java.util.Map;

public class AddPlaceApi {
    public static String pathParams = "/maps/api/place/add/json";

    public AddPlaceApi(){
    }

    public AddPlaceReq getPayload(){
        AddPlaceReq addPlaceReq = new AddPlaceReq();
        addPlaceReq.setAccuracy(50);
        addPlaceReq.setWebsite("http://google.com");
        return addPlaceReq;
    }

    public String getPlaceId(AddPlaceResp response){
        return response.getPlace_id();
    }



}
