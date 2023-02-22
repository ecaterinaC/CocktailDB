package assignment.example.step_definition;

import assignment.example.utilities.API_utils;
import assignment.example.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class cocktail_StepDef {

    Response response;
    JsonPath jsonPath;
    static String theDrinkName;
    static String theEndPoint;
    static int theStatusCode;
    List<Map<String,Object>> allCocktails;

    API_utils api_utils = new API_utils();

    @Given("the user wants to get information about {string}")
    public void theUserWantsToGetInformationAbout(String drinkName) {
        theDrinkName = drinkName;
    }
    @When("the user sends a GET request to {string}")
    public void the_user_sends_a_get_request_to(String endPoint) {
        theEndPoint = endPoint;

      response =  given().accept(ContentType.JSON)
                .queryParam(ConfigurationReader.getProperty("queryParamSearch"), theDrinkName)
                .when().get(endPoint)
                .then().statusCode(200)
                .extract().response();
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
       theStatusCode = statusCode;
       assertEquals(statusCode, response.statusCode());

    }

    @And("response body should include jsonSchema")
    public void responseBodyShouldIncludeJsonSchema() {

        api_utils.validateJsonSchema(ConfigurationReader.getProperty("queryParamSearch"),theDrinkName, theEndPoint,theStatusCode, "src/test/resources/schemas/cocktailsByName.json");
    }

    @Then("the response body should include drinks equals null")
    public void the_response_body_should_include_drinks_equals_null() {

        jsonPath = response.jsonPath();
        Map<String, Object> invalidDrink = jsonPath.getMap("");
        assertTrue("Drink list not Null",invalidDrink.containsValue(null));

    }

}
