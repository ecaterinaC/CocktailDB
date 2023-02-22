package assignment.example.step_definition;

import assignment.example.utilities.API_utils;
import assignment.example.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class typeOfBeverage_StepDef {

    Response response;
    JsonPath jsonPath;

    static int statusCode;
    List<Map<String,Object>> allDrinks;
    API_utils api_utils = new API_utils();

    @Given("the user sends a GET request to the CocktailDB API endpoint to search for a drink called {string}")
    public void the_user_sends_a_get_request_to_the_cocktail_db_api_endpoint_to_search_for_a_drink_called(String mocktail) {

        response =  given().accept(ContentType.JSON)
                .queryParam(ConfigurationReader.getProperty("queryParamSearch"), mocktail)
                .when().get(ConfigurationReader.getProperty("endPoint"))
                .then().statusCode(200)
                .extract().response();

    }

    @When("the API response is successful")
    public void the_api_response_is_successful() {
        statusCode = 200;

        assertEquals(statusCode, response.statusCode());
    }

    @When("the API response contains at least one drink")
    public void the_api_response_contains_at_least_one_drink() {

        jsonPath = response.jsonPath();
        allDrinks = jsonPath.getList("drinks");

       assertTrue("The list is empty, please choose a different beverage!", allDrinks.size() >= 1);

    }

    @Then("the strAlcoholic and strABV field in the API response should indicate the type")
    public void theStrAlcoholicAndStrABVFieldInTheAPIResponseShouldIndicateTheType() {

        api_utils.isNonAlcoholicDrink(response);
    }
}
