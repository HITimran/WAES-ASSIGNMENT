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
        this.storageId=getRandomDigit();
        response= postRequest( bodyContent,  storageId, sideName);
    }

    @Then("the GET response should contain type: {string} and Error {string} and ErrorMessage {string}")
    public void the_GET_response_should_contain_type_and_Error_and_ErrorMessage(String rCode,String errorType, String errorMsg) {
        ResponseCode rs=getResponseCode( rCode);
        response=getRequest(storageId);
        response.then().statusCode(rs.getStatusCode());
        if(rs.getStatusCode()==200)
        {
            assertEquals("The comparison type suppose to be equal ",
                        errorMsg, response.then().extract().body().jsonPath().get("detail"));

            assertEquals("The comparison type suppose to be equal ",
                    errorType, response.then().extract().body().jsonPath().get("type"));

        }
    }

    @Then("Validate the Response for side {string}")
    public void validate_the_Response_for_side(String exceptionType) {
        assertCodeAndReason(response,exceptionType);
    }

    @Given("a POST request was made with value {string} on {string}")
    public void a_POST_request_was_made_with_value_on(String bodyContent, String sideName) {
        this.storageId=getRandomDigit();
        response= postRequestContentBodyAsIs( bodyContent,  storageId, sideName);
    }

}
