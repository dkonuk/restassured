import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;



import static io.restassured.RestAssured.*;
import static io.restassured.internal.path.json.JsonPrettifier.prettifyJson;
import static org.hamcrest.Matchers.*;

public class PetStoreAPITest {


    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testAddNewPet() {
        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/pet");

        // Print the entire response
        System.out.println("Full Response:");
        response.then().log().all();

        // Print specific parts of the response
        System.out.println("\nStatus Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Pet ID: " + response.path("id"));
        System.out.println("Pet Name: " + response.path("name"));

        // Perform assertions
        response.then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("doggie"))
            .body("status", equalTo("available"))
            .body("category.name", equalTo("string"))
            .body("tags[0].name", equalTo("string"))
            .body("photoUrls", hasItem("string"));

        System.out.println("Structured Response:");
        System.out.println(prettifyJson(response.getBody().asString()));
    }

}