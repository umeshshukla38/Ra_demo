package common;
import config.Constants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import java.util.concurrent.TimeUnit;
import io.restassured.response.Response;

public class RequestHandler {

    private static Logger log = LoggerFactory.getLogger(RequestHandler.class);
    
    public Response createGetRequest(String url){
        Response response = RestAssured.given()
            .urlEncodingEnabled(true)
            // .log().all()
            .when().get(url);

        // response.prettyPrint();
        if(validateStatusCodeAndResponseTime(response, url)){
            return response;
        }

        return response;
    }

    /**
     * Post Request with Json Object
     * @param url
     * @param post_data
     * @return
     */
    public Response postDataInRequest(String url, JSONObject post_data) {
        EncoderConfig encoderconfig = new EncoderConfig();
        Response response = RestAssured.given()
            .config(RestAssured.config().encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .accept(ContentType.JSON)
            .header("Accept", "*/*")
            .header("Connection", "keep-alive")
            .header("Content-Type", "application/json")
            .body(post_data.toString())//.log().all()
            .post(url).then()
            .extract().response();

        if((response.getStatusCode() == 200 || response.getStatusCode() == 201) && response != null){
            return response;
        }
        return null;
    }

    /**
     * validate status code and response time
     * @param response
     * @return
     */
    private boolean validateStatusCodeAndResponseTime(Response response, String url){
        boolean validateBasics = false;
        if((response.getStatusCode() == 200) && response.getTimeIn(TimeUnit.SECONDS) <= Constants.RESPONSE_TIME){
            validateBasics = true;
        }

        if(response != null){
            if(validateBasics){
                log.info(url);
                return validateBasics;
            }else{
                validateBasics = false;
                log.info(url);
                log.info(response.asString());
                log.error("The get api call was taking more time than expected, total time taken : "+response.getTimeIn(TimeUnit.SECONDS));
            }
        }
        return validateBasics;
    }
}
