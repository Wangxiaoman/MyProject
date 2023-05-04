package com.tabledemo.op;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tabledemo.TableData;

/**
 * 
 * @author xiaomanwang
 *
 */
public class OrderOperator {

    public static final int ASC = 0;
    public static final int DESC = 1;

    public static List<List<Object>> orderby(TableData table, List<String> columns, int ascOrDesc) {
        if (table.tableEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> orderColumnIndexList = new ArrayList<>();
        for (String column : columns) {
            orderColumnIndexList.add(table.getColumnIndex(column));
        }

        Collections.sort(table.getRows(), new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                int v = opOrder(o1, o2, orderColumnIndexList);
                if (ascOrDesc > 0) {
                    return -v;
                }
                return v;
            }
        });
        return table.getRows();
    }

    private static int opOrder(List<Object> list1, List<Object> list2,
            List<Integer> orderColumnIndexList) {
        for (int i = 0; i < orderColumnIndexList.size(); i++) {
            Object o1 = list1.get(orderColumnIndexList.get(i));
            Object o2 = list2.get(orderColumnIndexList.get(i));
            int v = tableValueCompare(o1, o2);
            if(v != 0) {
                return v;
            }
        }
        return 0;
    }
    
    public static int tableValueCompare(Object o1, Object o2) {
        if (o1 instanceof Integer && o2 instanceof Integer) {
            int r = (Integer) o1 - (Integer) o2;
            int v = (r != 0 ? (r > 0 ? 1 : -1) : 0);
            if (v != 0) {
                return v;
            }
        }
        if (o1 instanceof Long && o2 instanceof Long) {
            long r = (Long) o1 - (Long) o2;
            int v = (r != 0 ? (r > 0 ? 1 : -1) : 0);
            if (v != 0) {
                return v;
            }
        }
        if (o1 instanceof Float && o2 instanceof Float) {
            float r = (Float) o1 - (Float) o2;
            int v = (r != 0 ? (r > 0 ? 1 : -1) : 0);
            if (v != 0) {
                return v;
            }
        }
        if (o1 instanceof Double && o2 instanceof Double) {
            double r = (Double) o1 - (Double) o2;
            int v = (r != 0 ? (r > 0 ? 1 : -1) : 0);
            if (v != 0) {
                return v;
            }
        }
        
        if(o1 instanceof String && o2 instanceof String) {
            int v = o1.toString().compareTo(o2.toString());
            if(v != 0) {
                return v;
            }
        }
        return 0;
    }

}
