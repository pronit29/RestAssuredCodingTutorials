Feature: Validate Place API's

  @Test
  Scenario: Verify if Place is being added using Add Place API
    Given Add Place payload is injected
    When User calls "Add" Place API with "POST" request
    Then The API call got success with status code as 200
    And "status" in the response body is "OK"