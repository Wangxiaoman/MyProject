package com.tabledemo;

import lombok.Data;

@Data
public class ColumnOpValue {
    private String column;
    private String op;
    private Object value;

    public ColumnOpValue(String column, String op, Object v) {
        this.column = column;
        this.op = op;
        this.value = v;
    }
}
