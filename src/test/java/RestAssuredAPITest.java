import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;

import org.junit.Assert.*;

public class RestAssuredAPITest {

    @Test
    public void getBooksDetails(){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com";

    // Get the RequestSpecification of the request to be sent to the server.
    RequestSpecification httpRequest = RestAssured.given()
            .queryParam("userName", "dkonuk")
            .queryParam("password", "19833131");

    // specify the method type (GET) and the parameters if any.
    Response response = httpRequest.when().post("/Account/v1/User"); // replace "/api/endpoint" with the actual endpoint

    // Print the status and message body of the response received from the server
    System.out.println("Status received => " + response.getStatusLine());
    //Assert.assertEquals(response.getStatusCode(), 200);
    System.out.println("Response=> " + response.prettyPrint());
        // specify the method type (GET) and the parameters if any.
	    //In this case the request does not take any parameters

        // Print the status and message body of the response received from the server

    }
    @Test
    public void generateuser(){
        //Create a JSON object to hold the request parameters
JSONObject requestBody = new JSONObject();
requestBody.put("userName", "dkonuk");
requestBody.put("password", "Tubis@1983");

// Use RestAssured to send the request
Response response = RestAssured.given()
        .header("Content-Type", "application/json")
        .body(requestBody.toString())
        .post("https://demoqa.com/Account/v1/User");

// Print the response body
System.out.println(response.getBody().asString());
    }
    @Test
    public void gettoken(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "dkonuk");
        requestBody.put("password", "Tubis@1983");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("https://demoqa.com/Account/v1/GenerateToken");

        System.out.println(response.getBody().asString());
    }
    public void getUser(){
        JSONObject requestBody = new JSONObject();
    }

}
//System.out.println("Status received => " + response.getStatusLine());
//        Assert.assertEquals(response.getStatusCode(), 200);
//        System.out.println("Response=> " + response.prettyPrint());