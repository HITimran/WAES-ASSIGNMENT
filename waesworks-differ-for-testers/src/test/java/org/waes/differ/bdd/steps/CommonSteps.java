package org.waes.differ.bdd.steps;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.waes.differ.utils.ResponseCode;
import org.waes.differ.utils.Sides;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.waes.differ.utils.StringLiterals.*;

public class CommonSteps {
    final static Logger log = Logger.getLogger(CommonSteps.class.getName());
    public static Response postRequest(String bodyContent, int storageId,Sides side)
    {
        System.out.println((String.format("post request on port "+PORT+" contentType "+CONTENT_TYPE+" SlideId "+storageId+" bodyContent "+bodyContent)));
       return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body("\""+bodyContent+"\"")
                .when()
                .post(ENDPOINTS +storageId+"/"+ side.name());
    }

    public static Response postRequest(String bodyContent, int storageId,String side)
    {
        System.out.println((String.format("post request on port "+PORT+" contentType "+CONTENT_TYPE+" SlideId "+storageId+" bodyContent "+bodyContent)));
        return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body("\""+bodyContent+"\"")
                .when()
                .post(ENDPOINTS +storageId+"/"+ side);
    }

    public static Response postRequestContentBodyAsIs(String bodyContent, int storageId,String side)
    {
        System.out.println((String.format("post request on port "+PORT+" contentType "+CONTENT_TYPE+" SlideId "+storageId+" bodyContent "+bodyContent)));
        return given()
                .port(PORT)
                .contentType(CONTENT_TYPE)
                .body(bodyContent)
                .when()
                .post(ENDPOINTS +storageId+"/"+ side);
    }


    public static Response getRequest(int storageId)
    {
       return given().
                port(PORT).
                contentType(CONTENT_TYPE).
                when().
                get(ENDPOINTS+storageId+"/");
    }

    public static void assertCodeAndReason(Response response, String rCode)
    {
        ResponseCode rs=getResponseCode( rCode);
        response.
            then().
            assertThat().
            statusCode(rs.getStatusCode());
        if(rs.getStatusCode()!=200&&rs.getStatusCode()!=500)
        {
            response.
                then().
                body(errorMessage,is(rs.getreason()));
        }
        System.out.println(errorMessage);
    }


    static ResponseCode getResponseCode(String rCode)
    {
        return rCode.equalsIgnoreCase(ResponseCode.BAD_REQUEST.name())?ResponseCode.BAD_REQUEST:
                rCode.equalsIgnoreCase(ResponseCode.BASE64Exception.name())?ResponseCode.BASE64Exception:
                        rCode.equalsIgnoreCase(ResponseCode.NOT_FOUND.name())?ResponseCode.NOT_FOUND:
                                rCode.equalsIgnoreCase(ResponseCode.NOT_IMPLEMENTED.name())?ResponseCode.NOT_IMPLEMENTED:
                                        rCode.equalsIgnoreCase(ResponseCode.OK.name())?ResponseCode.OK:
                                                rCode.equalsIgnoreCase(ResponseCode.UNSUPPORTED_MEDIA_TYPE_JSON.name())?ResponseCode.UNSUPPORTED_MEDIA_TYPE_JSON:
                                                        rCode.equalsIgnoreCase(ResponseCode.REQUEST_FAILED.name())?ResponseCode.REQUEST_FAILED:null;

    }



}
