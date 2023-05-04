package com.tabledemo;

import lombok.Data;

@Data
public class Column {
    public static final int COLUMN_INT = 1;
    public static final int COLUMN_VACHAR = 2;
    public static final int COLUMN_BOOLEAN = 3;
    
    private String name;
    private int type = COLUMN_INT;
    
    public Column(String name, int type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Column other = (Column) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
