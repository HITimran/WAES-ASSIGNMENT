@FunctionalScenarios
Feature: Functional Test for WebService

  @InitURL
  Scenario Outline: Test Scenarios for valid Comparision type
    Given a POST to http://localhost:8081/diffassign/v1/diff/<id>/left with value "<valueInLeft>"
    And a POST to http://localhost:8081/diffassign/v1/diff/<id>/right with "<valueInRight>"
    When sending a GET to http://localhost:8081/diffassign/v1/diff/<id>/
    Then the response should contain type: "<responseType>"

    Examples:
      | valueInLeft | valueInRight | id | responseType |
      | abcdefgh | abcdefgh |1|EQUAL|
      | 1234 | 123WE678 |2|DIFFERENT_LENGTH|
      | 1234 | ABCD |3|DIFFERENT_CHARS|

  @InitURL
  Scenario Outline: Test Scenarios in case only single side is filled
    Given a POST request was made with "<postInValue>" with side name as "<side>"
    Then the GET response should contain type: "<responseType>" and Error "<ComparisionType>" and ErrorMessage "<ErrorMessage>"

    Examples:
      | postInValue | side  | responseType | ComparisionType  | ErrorMessage |
      | abcdefgh    | right |     OK       | DIFFERENT_LENGTH | Left side contains no value. |
      | 1234        | left  | NO_CONTENT   |                  |                              |

  @InitURL
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
