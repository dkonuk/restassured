import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class zipCodeTest {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://zippopotam.us").
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
            log().body().
            assertThat().statusCode(200).
            assertThat().contentType(ContentType.JSON).
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
            assertThat().
            body("places[0].'place name'", equalTo(placeName));
    }


}
