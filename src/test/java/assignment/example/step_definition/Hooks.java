package assignment.example.step_definition;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void init() {

        RestAssured.baseURI = "https://www.thecocktaildb.com";
    }
}
