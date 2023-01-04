Feature: Validate Place API's

  @AddPlaceAPI
  Scenario Outline: Verify if Place is being added using Add Place API
    Given Add Place payload is injected with "<Name>", "<Language>", "<Address>"
    When User calls "Add" Place API with "POST" request
    Then The API call got success with status code as 200
    And "status" in the response body is "OK"

    Examples:
      |     Name    |  Language |       Address       |
      | Shark House |   English | 30, Park Ave Street |
      |   Hogwards  |   English | 509, Hogwards Avenue Street |

  @GetPlaceAPI
  Scenario Outline: Verify if Place is successfully added and displayed using the Get Place API
    Given Add Place payload is injected with "<Name>", "<Language>", "<Address>"
    When User calls "Add" Place API with "POST" request
    Then The API call got success with status code as 200
    And "status" in the response body is "OK"
    And Verify that the "place_id" is mapped to the "<Name>" using the "Get" Place API with "GET" request
    Then The API call got success with status code as 200

    Examples:
      |     Name    |  Language |       Address       |
      | Shark House |   English | 30, Park Ave Street |

  @DeletePlaceAPI
  Scenario Outline: Verify if Place is successfully added and displayed using the Get Place API
    Given Add Place payload is injected with "<Name>", "<Language>", "<Address>"
    When User calls "Add" Place API with "POST" request
    Then The API call got success with status code as 200
    And "status" in the response body is "OK"
    And Verify that the "place_id" is mapped to the "<Name>" using the "Get" Place API with "GET" request
    Then The API call got success with status code as 200
    And Delete Place payload is injected
    Then User calls "Delete" Place API with "DELETE" request
    And The API call got success with status code as 200
    And "status" in the response body is "OK"

    Examples:
      |     Name    |  Language |       Address       |
      | Shark House |   English | 30, Park Ave Street |
