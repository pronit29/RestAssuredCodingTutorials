package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    RequestSpecification req;

    public RequestSpecification requestSpecification() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        req = new RequestSpecBuilder().setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123")
                .build();
        return req;
    }
}
