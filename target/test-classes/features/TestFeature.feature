Feature: This feature allows to retrieve Cat Facts
  As a user
  I want to retrieve cat facts from the API
  So that I can learn interesting information about cats

  Scenario: Verify successful retrieval of cat facts
    When user sends a GET request to "/facts"
    Then the response status code should be 200
    And the response body should contain at least 1 cat fact