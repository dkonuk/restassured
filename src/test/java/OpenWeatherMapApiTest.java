import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.expect;
import static io.restassured.internal.path.json.JsonPrettifier.prettifyJson;
import static org.hamcrest.Matchers.*;

public class OpenWeatherMapApiTest {

    private static final String API_KEY = "5027f56e52e100a6ae96c02835b18d4a";
    private static final String CITY = "Kuşadası";

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
    }

    @Test
    public void testGetWeatherForCity() {
       Response response = given()
                .queryParam("q", CITY)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .queryParam("exclude", "minutely", "hourly", "alerts", "daily")
                .when()
                .get("/weather")
                .then()
               .statusCode(200)
            .extract().response();
                //.log().all();
       String cityName = response.path("name");
    Float temperature = response.path("main.temp");
    String weatherDescription = response.path("weather[0].description");
    Float windSpeed = response.path("wind.speed");

    // Print in a more readable format
    System.out.println("Weather for " + cityName + ":");
    System.out.println("Temperature: " + temperature + "°C");
    System.out.println("Description: " + weatherDescription);
    System.out.println("Wind Speed: " + windSpeed + " m/s");
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

         // Extract relevant information

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
