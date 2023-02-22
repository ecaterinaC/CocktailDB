package assignment.example.utilities;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class API_utils {


    /**
     * Validates the json schema of the API
     */

    public static void validateJsonSchema(String queryParam1, String queryParam2,String endPoint, int statusCode, String pathName){

        Response response = given().accept(ContentType.JSON)
                .queryParam(queryParam1,queryParam2)
                .when().get(endPoint)
                .then().statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(pathName)))
                .log().all().extract().response();

    }

    /**
     * @param queryParam1      queryParam1
     * @param queryParam2      queryParam2
     * @param endPoint         endPoint
     * @param statusCode       statusCode
     * @param index            index ingredients[0] || drinks[0]
     * @param pojoClass        pojoClass
     * @param <T>              return jsonObject
     * @return json object
     */

    public static <T> T getJsonObject(String queryParam1, String queryParam2, String endPoint, int statusCode, String index, Class<T> pojoClass){

       return given().accept(ContentType.JSON)
                .queryParam(queryParam1,queryParam2)
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).log()
                .body().extract().response().jsonPath().getObject(index, pojoClass);

    }

    public static void isNonAlcoholicDrink(Response response){
        String result = "";
        String strAlcoholic = response.jsonPath().getString("ingredients.strAlcoholic");
        String strABV = response.jsonPath().getString("ingredients.strABV");

        if( (strAlcoholic == null || strAlcoholic == "No") && strABV == null){
            result = "Non-Alcoholic Beverage!";
        } else if ( strAlcoholic == "Yes" && strABV != null) {
            result = "Alcoholic Beverage!";
        }

        System.out.println(result);
    }


}
