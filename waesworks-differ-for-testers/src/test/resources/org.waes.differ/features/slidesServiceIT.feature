@FunctionalScenarios
Feature: Functional Test for WebService

  @InitURL @ComparisionType
  Scenario Outline: Test Scenarios for valid Comparision type
    Given a POST to http://localhost:8081/diffassign/v1/diff/<id>/left with value "<valueInLeft>"
    And a POST to http://localhost:8081/diffassign/v1/diff/<id>/right with "<valueInRight>"
    When sending a GET to http://localhost:8081/diffassign/v1/diff/<id>/
    Then the response should contain type: "<responseType>"

    Examples:
      | valueInLeft              | valueInRight             | id | responseType |
      | abcdefgh                 | abcdefgh                 |1   |  EQUAL       |
      | cGxlYXN1cmUu             | cGxlYXN1cmUu             |2   |  EQUAL       |
      | N1cm                     | N1cm                     |3   |  EQUAL       |
      | 010101010101             | 010101010101             |4   |  EQUAL       |
      | ZzXxDdHhYyTtRrIi         | ZzXxDdHhYyTtRrIi         |5   |  EQUAL       |
      | YW55IGNhcm5hbCBwbGVhcwYU | YW55IGNhcm5hbCBwbGVhcwYU |6   |  EQUAL       |
      | SGkgdGhpcyBpbXJhbgUT     | SGkgdGhpcyBpbXJhbgUT     |7   |  EQUAL       |
      | cGxlYXN1cmUu             | cGxlYXN1cmUu             |8   |  EQUAL       |
      | 010101010101             | 010101010101             |9   |  EQUAL       |
      | ZzXxDdHhYyTtRrIi         | ZzXxDdHhYyTtRrIi         |10  |  EQUAL       |
      | 1234                     | 123WE678                 |11  |  DIFFERENT_LENGTH|
      | ABCDEFGH                 | ABCD                     |12  |  DIFFERENT_LENGTH|
      | 1234                     | ABCD                     |13  |  DIFFERENT_CHARS|


  @InitURL @SingleEmptySideResponse
  Scenario Outline: Test Scenarios in case only single side is filled
    Given a POST request was made with "<postInValue>" with side name as "<side>"
    Then the GET response should contain type: "<responseType>" and Error "<ComparisionType>" and ErrorMessage "<ErrorMessage>"

    Examples:
      | postInValue | side  | responseType | ComparisionType  | ErrorMessage |
      | abcdefgh    | right |     OK       | DIFFERENT_LENGTH | Left side contains no value. |
      | 1234        | left  | NO_CONTENT   |                  |                              |

  @InitURL @DIFFERENT_CHARS
  Scenario Outline: Test Scenarios for capturing characters difference
    Given a POST request was made with value "<postInLeft>" for left
    And a POST request was made with value "<postInRight>" for right
    Then the GET response should contain type: "<responseType>" and Error "<ComparisionType>" and ErrorMessage "<ErrorMessage>"

    Examples:
      | postInLeft  | postInRight  |   responseType  | ComparisionType |           ErrorMessage                       |
      | abcd        |    1234      |     OK          | DIFFERENT_CHARS |  Values are different on char(s) [0-3].      |
      | pqrstuvw    |    PQRSTUVW  |     OK          | DIFFERENT_CHARS |  Values are different on char(s) [0-7].      |
      | pqr12uvw    |    pqr34uvw  |     OK          | DIFFERENT_CHARS |  Values are different on char(s) [3-4].      |
      | pqr12uVw    |    pqr34uvw  |     OK          | DIFFERENT_CHARS |  Values are different on char(s) [3-4] [6].  |
      | pqr12uVw1234|    pqr34uvw2334|     OK        | DIFFERENT_CHARS |  Values are different on char(s) [3-4] [6] [8-9]. |
      | WAESwaes1234WAESwaes1234WAESwaes1234WAESwaes1234WAESwaes1234WAESwaes1234| WaESWaes1224WaESWaes1224WaESWaes1224WaESWaes1224WaESWaes1224WaESWaes1224| OK | DIFFERENT_CHARS |  Values are different on char(s) [1] [4] [10] [13] [16] [22] [25] [28] [34] [37] [40] [46] [49] [52] [58] [61] [64] [70]. |
      |             0AQuickBrownFoxJumpsRightOverTheLazyDog1                    |    1AQuickBrownFoxJumpsRightOverTheLazyDog0                             | OK | DIFFERENT_CHARS |  Values are different on char(s) [0] [39]. |

  @InitURL @IdNotFound
  Scenario Outline: Test Scenarios for capturing IdNotFound Exception
    Given a GET Request was made with <Id>
    Then the GET response should contain type: "<responseType>" and Error "<ComparisionType>" and ErrorMessage "<ErrorMessage>"

    Examples:
      |           Id       |   responseType        | ComparisionType |           ErrorMessage                  |
      |          1234      |     NOT_FOUND         | NOT_FOUND       |  ID 1234 not initialized.               |
      |3454345345433453312 |     NOT_FOUND         | NOT_FOUND       | ID 3454345345433453312 not initialized. |
