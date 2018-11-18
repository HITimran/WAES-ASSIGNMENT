package org.waes.differ.bdd.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.waes.differ.utils.Sides;

import static org.waes.differ.bdd.steps.CommonSteps.*;
import static org.waes.differ.bdd.steps.CommonSteps.postRequest;
import static org.waes.differ.utils.Helpers.Common.*;

public class stepDefExceptionType {
    Response responseLeft;
    Response responseRight;
    Response response;
    int storageId;

    @Given("a POST request was made with value {string} for left")
    public void a_POST_request_was_made_with_value_for_left(String bodyContent) {
        this.storageId=getRandomDigit();
        responseLeft=postRequest( bodyContent,  storageId, Sides.left);
    }

    @Given("a POST request was made with value {string} for right")
    public void a_POST_request_was_made_with_value_for_right(String bodyContent) {
        responseRight=postRequest( bodyContent,  storageId, Sides.right);
    }

    @Then("Validate the Response for sideLeft {string}")
    public void validate_the_Response_from_sideLeft(String exceptionType) {
        assertCodeAndReason(responseLeft,exceptionType);
    }

    @Then("Validate the Response for sideRight {string}")
    public void validate_the_Response_from_sideRight(String exceptionType) {
        assertCodeAndReason(responseRight,exceptionType);
    }

    @Given("a POST request was made with {string} with side name as {string}")
    public void a_POST_request_was_made_with_with_side_name_as(String bodyContent, String sideName) {
        response= postRequest( bodyContent,  getRandomDigit(), sideName);
    }

    @Then("Validate the Response for side {string}")
    public void validate_the_Response_for_side(String exceptionType) {
        assertCodeAndReason(response,exceptionType);
    }

    @Given("a POST request was made with value {string} on {string}")
    public void a_POST_request_was_made_with_value_on(String bodyContent, String sideName) {
        response= postRequestContentBodyAsIs( bodyContent,  getRandomDigit(), sideName);
    }
}
