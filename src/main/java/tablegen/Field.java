package tablegen;

import lombok.Data;

@Data
public class Field {
    private String name;
    private String type; // int(11), varchar(32) ... 
    private String key; // PRI UNI
}
