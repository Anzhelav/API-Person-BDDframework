Feature: This feature allows user to retrieve person information
###Check the person we created
  Scenario: Verify user can retrieve person information
    Given the person with ID 111 exists
    When user sends a GET request to "/{id}"
    Then verify response status code is 200
    And verify response body contains:
      | id   | name | age |
      | 111  | Ang  | 30  |
###Check non existing person
  Scenario: Verify user cannot retrieve information for a non-existent person
    Given the person with ID 222 doesn't exist
    When user sends a GET request to "/{id}"
    Then verify response status code is 404
    And verify response body contains:
      | status  | false            |
      | message | Person Not Found |
###Check Dummy person
  Scenario: Verify user can retrieve a dummy person
    When user sends a GET request to "/{id}/getDummy"
    Then verify response status code is 200
    And verify response body contains a dummy person object
###Check all persons
  Scenario: Verify user can retrieve all persons
    When user sends a GET request to "/getAll"
    Then verify response status code is 200
    And verify response body contains a list of all persons