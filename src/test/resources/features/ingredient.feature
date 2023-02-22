@runAll
Feature: Ingredient Search

  @wp3
  Scenario Outline: Get ingredients by name
    Given The user wants to get information about "vodka"
    When the user sends a GET request to endPoint
    Then the response status code is 200
    And response body should include following fields:

    Examples:
      | Ingredient ID |
      | Ingredient    |
      | Description   |
      | Type          |
      | Alcohol       |
      | ABV           |

    @wp4
    Scenario: Verify API response values are not null if case insensitive search
      Given The user searches for case insensitive ingredient
      When the user sends a GET request to endPoint
      Then the response status code is 200
      And validate response values are not null





