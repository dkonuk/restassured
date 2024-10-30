package models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private int id;
    private Category category;
    private String name;
    private String photoUrls;
    private Tag tags;
    private String status;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Category {
    private int id;
    private String name;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Tag {
    private int id;
    private String name;
}