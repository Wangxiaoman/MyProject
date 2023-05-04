package com.tabledemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.tabledemo.op.AggOperator;
import com.tabledemo.op.JoinOperator;
import com.tabledemo.op.OrderOperator;
import com.tabledemo.op.SelectOperator;

public class TableOpTestMain {
    public static void main(String[] args) {
        List<List<Object>> rows = new ArrayList<>();
        rows.add(Arrays.asList(1, 2, 3, "a"));
        rows.add(Arrays.asList(2, 2, 4, "b"));
        rows.add(Arrays.asList(3, 5, 2, "c"));
        rows.add(Arrays.asList(4, 3, 1, "d"));
        rows.add(Arrays.asList(2, 3, 1, "a"));
        rows.add(Arrays.asList(5, 3, 1, "a"));
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("c1", Column.COLUMN_INT));
        columns.add(new Column("c2", Column.COLUMN_INT));
        columns.add(new Column("c3", Column.COLUMN_INT));
        columns.add(new Column("c4", Column.COLUMN_VACHAR));
        TableData data = new TableData(columns, rows);
        
        List<List<Object>> t2Rows = new ArrayList<>();
        t2Rows.add(Arrays.asList(1, 8, 3));
        t2Rows.add(Arrays.asList(2, 2, 4));
        t2Rows.add(Arrays.asList(3, 5, 2));
        t2Rows.add(Arrays.asList(3, 3, 1));
        List<Column> t2Columns = new ArrayList<>();
        t2Columns.add(new Column("c1", Column.COLUMN_INT));
        t2Columns.add(new Column("c2", Column.COLUMN_INT));
        t2Columns.add(new Column("c3", Column.COLUMN_INT));
        TableData data2 = new TableData(t2Columns, t2Rows);

        // select
        ColumnOpValue cov = new ColumnOpValue("c2", "=", 3);
        List<ColumnOpValue> covs = Arrays.asList(cov);
        SQLCondition condition = new SQLCondition();
        condition.setCovs(covs);
        List<List<Object>> result = SelectOperator.select(data, condition);
        System.out.println(result);

        // agg
        try {
            Map<Object, Object> aggResult = AggOperator.aggGroup(data, "c4", AggOperator.OP_SUM, "c1");
            System.out.println("sum:" + aggResult);

            aggResult = AggOperator.aggGroup(data, "c4", AggOperator.OP_MAX, "c1");
            System.out.println("max:" + aggResult);

            aggResult = AggOperator.aggGroup(data, "c4", AggOperator.OP_MIN, "c1");
            System.out.println("min:" + aggResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // order by
        List<String> orderbyColumns = Arrays.asList("c4","c2");
        List<List<Object>> orderResult = OrderOperator.orderby(data, orderbyColumns, 1);
        System.out.println("order:" + orderResult);
        
        // table merge
        List<List<Object>> mergeLoopResult = JoinOperator.nestedLoopJoin(data, data2, "c1", "c1");
        System.out.println("merge loop:" + mergeLoopResult);
        
        List<List<Object>> mergeHashResult = JoinOperator.hashJoin(data, data2, "c1", "c1");
        System.out.println("merge hash:" + mergeHashResult);
        
        List<List<Object>> mergeSortedResult = JoinOperator.sortedJoin(data, data2, "c1", "c1");
        System.out.println("merge sorted:" + mergeSortedResult);
    }
}
