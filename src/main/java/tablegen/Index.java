package tablegen;

import java.util.List;

import lombok.Data;

@Data
public class Index {
    private String name;
    private String type; // PRIMARY KEY, UNIQUE KEY, KEY
    private List<Field> fields;
}
