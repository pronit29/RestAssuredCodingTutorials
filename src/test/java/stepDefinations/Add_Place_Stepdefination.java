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
import resources.TestDataBuilder;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class Add_Place_Stepdefination extends Utils {

    RequestSpecification req;
    Response response;
    RequestSpecification res;
    ResponseSpecification resspec;
    String resourceURI = "/maps/api/place/add/json";

    TestDataBuilder dataObj = new TestDataBuilder();


    @Given("^Add Place payload is injected$")
    public void add_place_payload_is_injected() {
         resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
         res = given().spec(requestSpecification())
                .body(dataObj.addPlacePayload());
    }
    @When("^User calls \"([^\"]*)\" Place API with \"([^\"]*)\" request$")
    public void user_calls_something_place_api_with_something_request(String strArg1, String strArg2) {

        response = res.when().post(resourceURI)
                .then().spec(resspec).extract().response();
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