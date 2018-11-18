@ExceptionScenarios
Feature: Functional Test to validate Exception type

  @InitURL
  Scenario Outline: Test Scenarios for Exception Type DataNotBase64Exception
    Given a POST request was made with value "<postInLeft>" for left
    And a POST request was made with value "<postInRight>" for right
    Then Validate the Response for sideLeft "<responseLeft>"
    And Validate the Response for sideRight "<responseRight>"

    Examples:
      | postInLeft | postInRight | responseLeft    | responseRight   |
      | qwe        | 32          | Base64Exception | Base64Exception |
      | 1          | qws         | Base64Exception | Base64Exception |
      | dat=       | 12_1rWeb    | Base64Exception | Base64Exception |
      | QWE$       | QSeRT       | Base64Exception | Base64Exception |
      | A\/1       | QWWW)233    | Base64Exception | Base64Exception |
      | QAZ Z      | ED34*(we    | Base64Exception | Base64Exception |

    @InitURL
  Scenario Outline: Test Scenarios for Exception Type JSON
    Given a POST request was made with value "<postInLeft>" for left
    And a POST request was made with value "<postInRight>" for right
    Then Validate the Response for sideLeft "<responseLeft>"
    And Validate the Response for sideRight "<responseRight>"

    Examples:
      | postInLeft | postInRight | responseLeft                | responseRight               |
      | O\wE       | O\     wE   | UNSUPPORTED_MEDIA_TYPE_JSON | UNSUPPORTED_MEDIA_TYPE_JSON |

  @InitURL
  Scenario Outline: Test Scenarios for Exception Type SideNameNotSupportedException
    Given a POST request was made with "<postInValue>" with side name as "<side>"
    Then Validate the Response for side "<resultantResponseLeft>"

    Examples:
      | postInValue | side   | resultantResponseLeft |
      | ABCD        | left   | OK                    |
      | ABCD        | right  | OK                    |
      | ABCD        | LEFT   | NOT_IMPLEMENTED       |
      | ABCD        | RIGHT  | NOT_IMPLEMENTED       |
      | ABCD        | Left   | NOT_IMPLEMENTED       |
      | ABCD        | Right  | NOT_IMPLEMENTED       |
      | ABCD        | 12345  | NOT_IMPLEMENTED       |
      | ABCD        | 4321   | NOT_IMPLEMENTED       |

  @InitURL
  Scenario Outline: Test Scenarios for Exception Type REQUEST_FAILED
    Given a POST request was made with value "<postInValue>" on "<side>"
    Then Validate the Response for side "<resultantResponseLeft>"

    Examples:
      | postInValue         | side  | resultantResponseLeft |
      | {\"key\":\"value\"} | right | REQUEST_FAILED        |
      | {}                  | right | REQUEST_FAILED        |
      | {}{}}               | left  | REQUEST_FAILED        |

  @InitURL
  Scenario Outline: Test Scenarios for Exception Type BadRequest
    Given a POST request was made with value "<postInValue>" on "<side>"
    Then Validate the Response for side "<resultantResponseLeft>"

    Examples:
      | postInValue | side | resultantResponseLeft |
      |             | left | BAD_REQUEST           |

  @InitURL
  Scenario Outline: Test Scenarios for Exception Type UNSUPPORTED_MEDIA_TYPE_JSON
    Given a POST request was made with value "<postInValue>" on "<side>"
    Then Validate the Response for side "<resultantResponseLeft>"

    Examples:
      | postInValue | side  | resultantResponseLeft       |
      | ''          | left  | UNSUPPORTED_MEDIA_TYPE_JSON |
      | 'ABCD'      | right | UNSUPPORTED_MEDIA_TYPE_JSON |
      | :           | right | UNSUPPORTED_MEDIA_TYPE_JSON |
      | {:}         | left  | UNSUPPORTED_MEDIA_TYPE_JSON |
      | {key:value} | left  | UNSUPPORTED_MEDIA_TYPE_JSON |