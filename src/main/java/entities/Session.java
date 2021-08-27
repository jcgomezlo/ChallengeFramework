package entities;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import utils.DataDeliver;
import utils.PathConstructor;

import static io.restassured.RestAssured.given;
public class Session {

    private static PathConstructor path = new PathConstructor();
    private static String sessionId;
    private static Logger LOGGER = Logger.getLogger(Session.class);

    public static void main(String[] args){
        System.out.println(getSession());
    }

    public static String getSession(){
        if(sessionId == null){
            LOGGER.info("Session not created");
            sessionId = setSession();
        }
        return sessionId;
    }

    private static String setSession(){
        Object[][] data = DataDeliver.getInstance("data/data.xlsx").getData("ValidCredentials");

        String request_token = given().get(path.getRequestTokenEndPoint())
                .then()
                .extract()
                .path("request_token");

        JSONObject request = new JSONObject();
        request.put("username",data[0][0]);
        request.put("password",data[0][1]);
        request.put("request_token",request_token);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(path.getSessionWithLoginEndPoint())
                .then()
                .statusCode(200);

        request = new JSONObject();
        request.put("request_token",request_token);

         String sessionIdAux = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(path.getNewSessionEndPoint())
                .then()
                .statusCode(200)
                .extract()
                .path("session_id");

         return sessionIdAux;


    }
}
