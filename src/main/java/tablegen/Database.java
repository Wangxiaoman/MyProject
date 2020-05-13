package tablegen;

import java.util.List;

import lombok.Data;

@Data
public class Database {
    private String name;
    private List<Table> tables;
}
