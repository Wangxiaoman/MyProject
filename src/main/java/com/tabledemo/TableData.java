package com.tabledemo;

import java.util.List;

import lombok.Data;

@Data
public class TableData {
    private List<Column> columns;
    private List<List<Object>> rows;

    public TableData(List<Column> columns, List<List<Object>> cells) {
        this.columns = columns;
        this.rows = cells;
    }

    public int tableSize() {
        if(tableEmpty()) {
            return 0;
        }
        return this.rows.size();
    }
    
    public boolean tableEmpty() {
        if (rows == null || rows.isEmpty()) {
            return true;
        }
        return false;
    }

    public int getColumnIndex(String columnName) {
        for (int i = 0; i < this.columns.size(); i++) {
            if (columns.get(i).getName().equals(columnName)) {
                return i;
            }
        }
        return -1;
    }
}
