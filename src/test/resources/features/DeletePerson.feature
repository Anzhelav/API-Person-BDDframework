Feature: This feature allows to delete a Person and their information from the system using API


  Scenario: Verify user can delete a person
    Given the person with ID 111 exists
    When user sends a DELETE request to "/{id}/delete"
    Then verify response status code is 204
    And verify response body contains:
      | status  | true                      |
      | message | Person deleted successfully |

  Scenario: Verify user cannot delete a non-existent person
    Given the person with ID 777 doesn't exist
    When user sends a DELETE request to "/{id}/delete"
    Then verify response status code is 200
    And verify response body contains:
      | status  | false               |
      | message | Person Doesn't Exist |