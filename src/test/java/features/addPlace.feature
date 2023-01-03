Feature: Validate Place API's

  @Test
  Scenario Outline: Verify if Place is being added using Add Place API
    Given Add Place payload is injected with "<Name>", "<Language>", "<Address>"
    When User calls "Add" Place API with "POST" request
    Then The API call got success with status code as 200
    And "status" in the response body is "OK"

    Examples:
      |     Name    |  Language |       Address       |
      | Shark House |   English | 30, Park Ave Street |
      |   Hogwards  |   English | 509, Hogwards Avenue Street |