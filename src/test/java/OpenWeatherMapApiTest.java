import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;

public class OpenWeatherMapApiTest {

    private static final String API_KEY = "";
    private static final String CITY = "Ankara";

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
    }

    @Test
    public void testGetWeatherForCity() {
        given()
                .queryParam("q", CITY)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .when()
                .get("/weather")
                .then()
                .log().all();
    }
    @Test
    public void testInvalidApiKey() {
        given()
                .queryParam("q", CITY)
                .queryParam("appid", "invalidApiKey")
                .queryParam("units", "metric")
                .when()
                .get("/weather")
                .then()
                .statusCode(401)
                .log().all();
    }
    @Test
    public void testInvalidCity() {
        given()
                .queryParam("q", "invalidCity")
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .when()
                .get("/weather")
                .then()
                .statusCode(404);
    }
}
