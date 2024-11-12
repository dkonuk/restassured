package models;
import lombok.Data;
import lombok.Builder;

@Data
public class Place {
    private String placeName;
    private String longitude;
    private String latitude;
    private String state;
    private String stateAbbreviation;

}
