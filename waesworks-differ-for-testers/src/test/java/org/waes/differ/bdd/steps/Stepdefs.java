package org.waes.differ.bdd.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.waes.differ.utils.Sides;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.waes.differ.bdd.steps.CommonSteps.getRequest;
import static org.waes.differ.bdd.steps.CommonSteps.postRequest;
import static org.waes.differ.utils.StringLiterals.*;

public class Stepdefs {
    Response getResponse;

    @Before("@InitURL")
    public void initiateRequest()
    {
        RestAssured.baseURI = HTTP_LOCALHOST_DIFFASSIGN;
    }

    @Given("a POST to http:\\/\\/localhost:{int}\\/diffassign\\/v{int}\\/diff\\/{int}\\/left with value {string}")
    public void postingToLocalHostLeft(Integer int1, Integer int2, Integer storageId, String bodyContent) {

        postRequest( bodyContent,  storageId,Sides.left)
           .then().assertThat().statusCode(200)
                   .and()
                   .body(Sides.left.name(),is(bodyContent));
    }

    @Given("a POST to http:\\/\\/localhost:{int}\\/diffassign\\/v{int}\\/diff\\/{int}\\/right with {string}")
    public void postingToLocalHostRight(Integer int1, Integer int2, Integer storageId, String bodyContent) {
        postRequest( bodyContent,  storageId,Sides.right)
                .then().assertThat().statusCode(200)
                .and()
                .body(Sides.right.name(),is(bodyContent));
    }

    @When("sending a GET to http:\\/\\/localhost:{int}\\/diffassign\\/v{int}\\/diff\\/{int}\\/")
    public void GetRequestFromLocalHost(Integer int1, Integer int2, Integer storageId) {
        getResponse=getRequest(storageId);
        getResponse.then()
                .assertThat().statusCode(200);
    }

    @Then("the response should contain type: {string}")
    public void the_response_should_contain_type(String string) {
        getResponse.then().statusCode(200);
        assertEquals("The comparison type suppose to be equal ",
                string, getResponse.then().extract().body().jsonPath().get("type"));
    }
}