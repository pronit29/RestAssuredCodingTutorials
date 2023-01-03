package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class Add_Place_Stepdefination extends Utils {

    RequestSpecification req;
    Response response;
    RequestSpecification res;
    ResponseSpecification resspec;
    APIResources resourcesObj;

    TestDataBuilder dataObj = new TestDataBuilder();

    @Given("Add Place payload is injected with {string}, {string}, {string}")
    public void add_place_payload_is_injected_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(dataObj.addPlacePayload(name, language, address));
    }

    @When("^User calls \"([^\"]*)\" Place API with \"([^\"]*)\" request$")
    public void user_calls_something_place_api_with_something_request(String resources, String httpMethods) {

        resourcesObj = APIResources.valueOf(resources);

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        if(httpMethods.equalsIgnoreCase("POST"))
        response = res.when().post(resourcesObj.getResource());
        else if(httpMethods.equalsIgnoreCase("GET"))
            response = res.when().get(resourcesObj.getResource());
        else if(httpMethods.equalsIgnoreCase("DELETE"))
            response = res.when().delete(resourcesObj.getResource());

    }

    @Then("The API call got success with status code as {int}")
    public void the_api_call_got_success_with_status_code_as(int expectedStatusCode) {
        assertEquals(response.getStatusCode(),expectedStatusCode);
    }


    @And("^\"([^\"]*)\" in the response body is \"([^\"]*)\"$")
    public void something_in_the_response_body_is_something(String statusKey, String statusValue) {

        String resp = response.asString();
        System.out.println(resp);
        JsonPath js = new JsonPath(resp);
        System.out.println(js.get(statusKey).toString());
        assertEquals(js.get(statusKey).toString(),statusValue);
    }
}