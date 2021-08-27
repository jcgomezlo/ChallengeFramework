import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PathConstructor;
import data.AuthenticationDataProvider;

import static io.restassured.RestAssured.given;

public class Authentication extends AuthenticationDataProvider {

    private static PathConstructor path = new PathConstructor();
    private static Logger LOGGER = Logger.getLogger(Authentication.class);

    @Test(dataProvider = "ValidCredentials")
    public void loginSuccessful(String username, String password){

        LOGGER.info("Test Started");
        String request_token = given().get(path.getRequestTokenEndPoint())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("request_token");

        LOGGER.info("First request executed, request_token = " + request_token);

        Assert.assertNotNull(request_token);
        JSONObject request = new JSONObject();
        request.put("username",username);
        request.put("password",password);
        request.put("request_token",request_token);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(path.getSessionWithLoginEndPoint())
                .then()
                .statusCode(200);

        LOGGER.info("Login Successful " + username + " , trying to create session now ...");
        request = new JSONObject();
        request.put("request_token",request_token);

        String sessionId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(path.getNewSessionEndPoint())
                .then()
                .statusCode(200)
                .extract()
                .path("session_id");

        Assert.assertNotNull(sessionId);

        LOGGER.info("Session Created " + sessionId );
    }

    @Test(dataProvider = "InvalidCredentials")
    public void loginInvalid(String username, String password){

        LOGGER.info("Test Started");
        String request_token = given().get(path.getRequestTokenEndPoint())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("request_token");

        LOGGER.info("First request executed, request_token = " + request_token);

        Assert.assertNotNull(request_token);
        JSONObject request = new JSONObject();
        request.put("username",username);
        request.put("password",password);
        request.put("request_token",request_token);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(path.getSessionWithLoginEndPoint())
                .then()
                .statusCode(401);

        LOGGER.info("Login Invalid");

    }
}
