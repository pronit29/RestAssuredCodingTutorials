package resources;

import addplacepojo.GMapsSerialize;
import addplacepojo.LocationPOJO;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public GMapsSerialize addPlacePayload() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Initializing the object of the base POJO class
        GMapsSerialize gmapSerial = new GMapsSerialize();
        LocationPOJO locPoj = new LocationPOJO();
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        locPoj.setLat(-23.443432);
        locPoj.setLng(43.3534523);

        //Inserting data into the JSON which we are creating using the POJO class
        gmapSerial.setAccuracy(50);
        gmapSerial.setAddress("29, side layout, cohen 09");
        gmapSerial.setLanguage("French-IN");
        gmapSerial.setPhone_number("(+91) 983 893 3937");
        gmapSerial.setWebsite("http://google.com");
        gmapSerial.setName("Frontline house");
        gmapSerial.setTypes(myList);
        gmapSerial.setLocation(locPoj);

        return gmapSerial;
    }


}