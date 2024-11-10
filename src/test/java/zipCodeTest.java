import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class zipCodeTest {

    @Test
    public void requestZipCodeExpectHttp200(){
        given().
        when().
            get("https://zippopotam.us/us/90210").
        then().
            assertThat().statusCode(200).
            assertThat().contentType(ContentType.JSON);

    }

}
