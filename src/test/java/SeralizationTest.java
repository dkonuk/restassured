import org.testng.Assert;
import org.testng.annotations.Test;
import models.Location;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.hasSize;

public class SeralizationTest {

    @Test
    public void seralizationTest() {
        Location location =
                given().
                when().
                get("https://zippopotam.us/us/90210").
                as(Location.class);

        Assert.assertEquals(
                "Beverly Hills",
                location.getPlaces().get(0).getPlaceName()
        );
    }
}
