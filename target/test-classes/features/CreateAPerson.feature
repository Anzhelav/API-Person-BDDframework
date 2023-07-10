Feature: This feature allows user to add Person and store their information in the system

  Scenario: Verify user can add a person
    Given the person information:
      | id  | name | age |
      | 111 | Ang  | 30  |
    When user sends a POST request to "/add"
    Then the response status code should be 201
    And the response body should contain:
      | status  | true                        |
      | message | Person created successfully |

  Scenario: Verify user cannot add a person with invalid data
    Given the person information:
      | id  | name | age |
      | 555 | -1   | 5   |
         # Invalid name value
    When user sends a POST request to "/add"
    Then the response status code should be 400
    And the response body should contain:
      | status  | false             |
      | message | Invalid name value |