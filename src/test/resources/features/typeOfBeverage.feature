Feature: Verify alcoholic or non-alcoholic drink in CocktailDB API

  @wp5
  Scenario: Verify that the drink is alcoholic or non-alcoholic
    Given the user sends a GET request to the CocktailDB API endpoint to search for a drink called "coffee"
    When the API response is successful
    And the API response contains at least one drink
    Then the strAlcoholic and strABV field in the API response should indicate the type

