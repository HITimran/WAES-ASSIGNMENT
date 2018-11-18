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