@runAll
Feature: Cocktail Database API

    @wp1
  Scenario: Get cocktail by name
    Given the user wants to get information about "margarita"
    When the user sends a GET request to "/api/json/v1/1/search.php"
    Then the response status code should be 200
    And response body should include jsonSchema

    @wp2
  Scenario: Verify invalid cocktail name
    Given the user wants to get information about "aswdfs"
    When the user sends a GET request to "/api/json/v1/1/search.php"
    Then the response status code should be 200
    And the response body should include drinks equals null


