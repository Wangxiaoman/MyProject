package com.tabledemo;

import java.util.List;

import lombok.Data;

@Data
public class SQLCondition {
    private List<ColumnOpValue> covs;
    private List<String> groupbyColumns; 
    private List<String> orderbyColumns;
    private int limit;
    private int offset;
}
