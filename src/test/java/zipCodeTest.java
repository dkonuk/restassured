import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;


import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class zipCodeTest {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://zippopotam.us").
                build();
    }
    @BeforeClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @DataProvider(name = "zipCodeandPlaces")
    public static Object[][] zipCodeandPlaces(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"tr", "09100", "Köprülü Mah."}
        };
    }

    @Test
    public void requestZipCodeExpectHttp200(){
        given().
                spec(requestSpec).
                log().all().
        when().
            get("us/90210").
        then().
            spec(responseSpec).
            log().body().
            assertThat().body("places.'state'", hasItem("California")).
            assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

    }
//Test using dataprovider to test different zipcode values with only one test class
    @Test(dataProvider = "zipCodeandPlaces")
    public void requestMultipleZipCodes(String countryCode, String zipCode, String placeName){
        given().
                spec(requestSpec).
                pathParam("countryCode", countryCode).
                pathParam("zipCode", zipCode).
        when().
            get("{countryCode}/{zipCode}").
        then().
            spec(responseSpec).
            assertThat().
            body("places[0].'place name'", equalTo(placeName));
    }

    @Test
    public void requestZipCodeSaveResponse(){
        String placeName =
                given().
                spec(requestSpec).
                pathParam("countryCode", "us").
                pathParam("zipCode", "90210").
                when().
                get("{countryCode}/{zipCode}").
                then().
                spec(responseSpec).
                extract().
                path("places[0].'place name'");
        System.out.println(placeName);
        Assert.assertEquals(placeName, "Beverly Hills");

    }


}
