Feature: This feature allows user to add Person and store their information in the system

  Scenario: Verify user can add a person
    Given the person information:
      | id  | name | age |
      | 111 | Ang  | 30  |
    When user sends a POST request to "/add"
    Then verify response status code is 201
    And verify response body contains:
      | status  | true                        |
      | message | Person created successfully |
### Negative test case
  Scenario: Verify user cannot add a person with an existing ID
    Given the person information:
      | id  | name | age |
      | 111 | Ang  | 30  |
    # Invalid id value
    When user sends a POST request to "/add"
    Then verify response status code is 400
    And verify response body contains:
      | status  | false                 |
      | message | Person Already Exists |

