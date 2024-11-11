import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.internal.Utils.log;

public class zipCodeTest {

    @DataProvider(name = "zipCodeandPlaces")
    public static Object[][] zipCodeandPlaces(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    public void requestZipCodeExpectHttp200(){
        given().
                log().all().
        when().
            get("https://zippopotam.us/us/90210").
        then().
            log().body().
            assertThat().statusCode(200).
            assertThat().contentType(ContentType.JSON).
            assertThat().body("places.'state'", hasItem("California")).
            assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

    }
//Test using dataprovider to test different zipcode values with only one test class
    @Test(dataProvider = "zipCodeandPlaces")
    public void requestZipCodeExpectHttp200(String countryCode, String zipCode, String placeName){
        given().
                pathParam("countryCode", countryCode).
                pathParam("zipCode", zipCode).
        when().
            get("https://zippopotam.us/{countryCode}/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(placeName));
    }


}
