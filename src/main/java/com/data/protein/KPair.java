package com.data.protein;

import lombok.Data;

@Data
public class KPair {
    private String k1;
    private String k2;
    private int c1;
    private int c2;
    private int pCount;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KPair other = (KPair) obj;
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
        return "KPair [k1=" + k1 + ", k2=" + k2 + ", c1=" + c1 + ", c2=" + c2 + ", pCount=" + pCount
                + "]";
    }
}
