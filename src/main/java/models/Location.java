package models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonPropertyOrder({"post Code", "country", "countryAbbreviation", "places"})
public class Location {
    private String postCode;
    private String country;
    private String countryAbbreviation;
    private List<Place> places;

    public Location(){
        Place place = new Place();
        List<Place> places = new ArrayList<>();
        places.add(place);

        this.places = places;
    }
    @JsonProperty("post code")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("post code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}


