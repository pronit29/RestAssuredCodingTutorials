package SpecBuilderTests;

import SerializeTestsPOJO.GMapsSerialize;
import SerializeTestsPOJO.LocationPOJO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    public static void main(String args[]) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String resourceURI = "/maps/api/place/add/json";

        //Initializing the object of the base POJO class
        GMapsSerialize gmapSerial = new GMapsSerialize();
        LocationPOJO locPoj = new LocationPOJO();
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        locPoj.setLatitude(-23.443432);
        locPoj.setLongitude(43.3534523);

        //Inserting data into the JSON which we are creating using the POJO class
        gmapSerial.setAccuracy(50);
        gmapSerial.setAddress("29, side layout, cohen 09");
        gmapSerial.setLanguage("French-IN");
        gmapSerial.setPhone_number("(+91) 983 893 3937");
        gmapSerial.setWebsite("http://google.com");
        gmapSerial.setName("Frontline house");
        gmapSerial.setTypes(myList);
        gmapSerial.setLocation(locPoj);

        //Instantiating the object for the Request Spec Builder
        RequestSpecification req = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                        .setBaseUri(RestAssured.baseURI)
                                .addQueryParam("key", "qaclick123")
                                        .build();

        //Instantiating the object for the Response Spec Builder

        ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                        .expectStatusCode(200).build();

        //Given
        RequestSpecification request = given().spec(req)
                .body(gmapSerial);

                //When
        String response = request.when().post(resourceURI)

                //Then
                .then().spec(res).extract().response().asString();
        System.out.println(response);

    }
}
