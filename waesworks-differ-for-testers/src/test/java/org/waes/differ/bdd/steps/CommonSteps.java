package org.waes.differ.bdd.steps;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.waes.differ.utils.ResponseCode;
import org.waes.differ.utils.Sides;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.waes.differ.utils.StringLiterals.*;

public class CommonSteps {
    final static Logger log = Logger.getLogger(CommonSteps.class.getName());
    public static Response postRequest(String bodyContent, long storageId,Sides side)
    {
       log.info(String.format(String.format("POST request on side %s contentType %s SlideId %s bodyContent %s ",side.name(),CONTENT_TYPE,storageId,bodyContent)));
       return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body("\""+bodyContent+"\"")
                .when()
                .post(ENDPOINTS +storageId+"/"+ side.name());
    }

    public static Response postRequest(String bodyContent, long storageId,String side)
    {
        log.info(String.format(String.format("POST request on side %s contentType %s SlideId %s bodyContent %s ",side,CONTENT_TYPE,storageId,bodyContent)));
        return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body("\""+bodyContent+"\"")
                .when()
                .post(ENDPOINTS +storageId+"/"+ side);
    }

    public static Response postRequestContentBodyAsIs(String bodyContent, long storageId,String side)
    {
        log.info(String.format(String.format("POST request on side %s contentType %s SlideId %s bodyContent %s ",side,CONTENT_TYPE,storageId,bodyContent)));
        return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body(bodyContent)
                .when()
                .post(ENDPOINTS +storageId+"/"+ side);
    }

    public static Response getRequest(long storageId)
    {
        log.info(String.format(String.format("GET request from SlideId %s",storageId)));
       return given().
                port(PORT).
                contentType(CONTENT_TYPE).
                when().
                get(ENDPOINTS+storageId+"/");
    }

    public static void assertCodeAndReason(Response response, String rCode)
    {
        ResponseCode rs=getResponseCode( rCode);
        log.info(String.format(String.format("Assertion GET Response for status to be %s",rs.getStatusCode())));

        response.
            then().
            assertThat().
            statusCode(rs.getStatusCode());

        if(rs.getStatusCode()!=200&&rs.getStatusCode()!=500)
        {

            log.info(String.format(String.format("Assertion GET Response for errorMessage to be %s",rs.getreason())));
            response.
                then().
                body(errorMessage,is(rs.getreason()));
        }
    }

    public static void assertCodeAndReasonMessage(Response response,String rCode,String errorType, String errorMsg)
    {
        ResponseCode rs=getResponseCode( rCode);

        response.then().statusCode(rs.getStatusCode());
        if(rs.getStatusCode()==200)
        {
            assertEquals("The comparison type suppose to be equal ",
                    errorMsg, response.then().extract().body().jsonPath().get("detail"));

            assertEquals("The comparison type suppose to be equal ",
                    errorType, response.then().extract().body().jsonPath().get("type"));

        }else if(rs.getStatusCode()==404){
            assertEquals("The comparison type suppose to be equal ",
                    errorMsg, response.then().extract().body().jsonPath().get("errorMessage"));
        }
    }

    public static ResponseCode getResponseCode(String rCode)
    {
        return rCode.equalsIgnoreCase(ResponseCode.BAD_REQUEST.name())?ResponseCode.BAD_REQUEST:
                rCode.equalsIgnoreCase(ResponseCode.BASE64Exception.name())?ResponseCode.BASE64Exception:
                        rCode.equalsIgnoreCase(ResponseCode.NOT_FOUND.name())?ResponseCode.NOT_FOUND:
                                rCode.equalsIgnoreCase(ResponseCode.NOT_IMPLEMENTED.name())?ResponseCode.NOT_IMPLEMENTED:
                                        rCode.equalsIgnoreCase(ResponseCode.OK.name())?ResponseCode.OK:
                                                rCode.equalsIgnoreCase(ResponseCode.UNSUPPORTED_MEDIA_TYPE_JSON.name())?ResponseCode.UNSUPPORTED_MEDIA_TYPE_JSON:
                                                        rCode.equalsIgnoreCase(ResponseCode.REQUEST_FAILED.name())?ResponseCode.REQUEST_FAILED:
                                                                rCode.equalsIgnoreCase(ResponseCode.MethodNotAllowed.name())?ResponseCode.MethodNotAllowed:
                                                                rCode.equalsIgnoreCase(ResponseCode.NO_CONTENT.name())?ResponseCode.NO_CONTENT:null;

    }



}
