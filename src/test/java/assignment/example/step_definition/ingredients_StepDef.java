package assignment.example.step_definition;

import assignment.example.pojo.IngredientPOJO;
import assignment.example.utilities.API_utils;
import assignment.example.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class ingredients_StepDef {

    Response response;
    static String theIngredient;
    IngredientPOJO ingredientPOJO;
    JsonPath jsonPath;
    API_utils api_utils = new API_utils();

    static int theStatusCode;

    @Given("The user wants to get information about {string}")
    public void the_user_wants_to_get_information_about(String ingredient) {
        theIngredient = ingredient;
    }

    @When("the user sends a GET request to endPoint")
    public void theUserSendsAGETRequestToEndPoint() {

         response = given().accept(ContentType.JSON)
                .queryParam(ConfigurationReader.getProperty("queryParamIngredient"), theIngredient)
                .when().get(ConfigurationReader.getProperty("endPoint"))
                .then().statusCode(200)
                .extract().response();
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int statusCode) {
        theStatusCode = statusCode;
        assertEquals(statusCode, response.statusCode());

    }


    @And("response body should include following fields:")
    public void responseBodyShouldIncludeFollowingFields() {
        int expectedStatusCode = 200;

       ingredientPOJO = api_utils.getJsonObject(ConfigurationReader.getProperty("queryParamIngredient"), theIngredient,ConfigurationReader.getProperty("endPoint"), expectedStatusCode, "ingredients[0]", IngredientPOJO.class);

    }

    @Given("The user searches for case insensitive ingredient")
    public void theUserSearchesForCaseInsensitiveIngredient() {


        ingredientPOJO = api_utils.getJsonObject(ConfigurationReader.getProperty("queryParamIngredient"), ConfigurationReader.getProperty("caseInsensitiveIngredient"), ConfigurationReader.getProperty("endPoint"), theStatusCode, "ingredients[0]", IngredientPOJO.class);

        assertNotNull("Null values present",ingredientPOJO);
    }

    @And("validate response values are not null")
    public void validateResponseValuesAreNotNull() {
    }

}
