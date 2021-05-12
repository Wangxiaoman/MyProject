package com.data.protein;

import lombok.Data;

@Data
public class Keywords {
    private String k1;
    private int count1;
    private String k2;
    private int count2;
    private int pairCount;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Keywords other = (Keywords) obj;
        if (k1 == null) {
            if (other.k1 != null)
                return false;
        } else if (!k1.equals(other.k1))
            return false;
        if (k2 == null) {
            if (other.k2 != null)
                return false;
        } else if (!k2.equals(other.k2))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((k1 == null) ? 0 : k1.hashCode());
        result = prime * result + ((k2 == null) ? 0 : k2.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "'"+ k1 + "','" + k2 + "'";
    }
}
