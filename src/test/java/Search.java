import data.MoviesProvider;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import org.testng.annotations.Test;
import utils.PathConstructor;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class Search extends MoviesProvider {

    private static PathConstructor path = new PathConstructor();
    private static Logger LOGGER = Logger.getLogger(Search.class);

    @Test(dataProvider = "Movies")
    public void search(String movie, String number){

        LOGGER.info("Starting search of " + movie);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(path.getSearchEndPoint(movie))
                .then()
                .statusCode(200)
                .body("total_results", equalTo(Integer.parseInt(number)));
    }


}
