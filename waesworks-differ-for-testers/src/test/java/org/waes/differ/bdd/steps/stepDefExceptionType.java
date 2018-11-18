package org.waes.differ.bdd.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.waes.differ.utils.ResponseCode;
import org.waes.differ.utils.Sides;

import static org.junit.Assert.assertEquals;
import static org.waes.differ.bdd.steps.CommonSteps.*;
import static org.waes.differ.bdd.steps.CommonSteps.postRequest;
import static org.waes.differ.utils.Helpers.Common.*;

public class stepDefExceptionType {
    Response responseLeft;
    Response responseRight;
    Response response;
    Long storageId;

    @Given("a POST request was made with value {string} for left")
    public void postingRequestDefaultHostOnLeftSideRandomId(String bodyContent) {
        this.storageId=getRandomDigit();
        responseLeft=postRequest( bodyContent,  storageId, Sides.left);
    }

    @Given("a POST request was made with value {string} for right")
    public void postingRequestDefaultHostOnRightSideRandomId(String bodyContent) {
        responseRight=postRequest( bodyContent,  storageId, Sides.right);
    }

    @Then("Validate the Response for sideLeft {string}")
    public void validateTheResponseAfterPostRequestForSideLeft(String exceptionType) {
        assertCodeAndReason(responseLeft,exceptionType);
    }

    @Then("Validate the Response for sideRight {string}")
    public void validateTheResponseAfterPostRequestForSideRight(String exceptionType) {
        assertCodeAndReason(responseRight,exceptionType);
    }

    @Given("a POST request was made with {string} with side name as {string}")
    public void postingRequestDefaultHostOnAnySideRandomId(String bodyContent, String sideName) {
        this.storageId=getRandomDigit();
        response= postRequest( bodyContent,  storageId, sideName);
    }

    @Given("a POST request was made with value {string} on {string}")
    public void postingRequestDefaultHostOnAnySideRandomIdButStringAsIs(String bodyContent, String sideName) {
        this.storageId=getRandomDigit();
        response= postRequestContentBodyAsIs( bodyContent,  storageId, sideName);
    }

    @Given("a GET Request was made with {long}")
    public void getRequestToStoreID(long storageId) {
        this.storageId=storageId;
    }

    @Then("the GET response should contain type: {string} and Error {string} and ErrorMessage {string}")
    public void ValidateGetRequestForStatusCodeErrorTypeErrorMessage(String rCode,String errorType, String errorMsg) {
        response=getRequest(storageId);
        assertCodeAndReasonMessage(response,rCode, errorType, errorMsg);
    }

    @Then("Validate the Response for side {string}")
    public void validateTheResponseForAnySide(String exceptionType) {
        assertCodeAndReason(response,exceptionType);
    }




}
