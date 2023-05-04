package com.tabledemo.op;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.tabledemo.TableData;

public class AggOperator {

    public static final String OP_SUM = "sum";
    public static final String OP_MIN = "min";
    public static final String OP_MAX = "max";

    private static Object opObject(Object o1, Object o2, String op) throws Exception {
        if (o1 == null) {
            if (Objects.equal(OP_SUM, op)) {
                o1 = 0;
            } else {
                o1 = o2;
            }
        }
        try {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                if (Objects.equal(OP_SUM, op)) {
                    return (Integer) o1 + (Integer) o2;
                } else if (Objects.equal(OP_MIN, op)) {
                    return Math.min((Integer) o1, (Integer) o2);
                } else if (Objects.equal(OP_MAX, op)) {
                    return Math.max((Integer) o1, (Integer) o2);
                }
            }
            if (o1 instanceof Long && o2 instanceof Long) {
                if (Objects.equal(OP_SUM, op)) {
                    return (Long) o1 + (Long) o2;
                } else if (Objects.equal(OP_MIN, op)) {
                    return Math.min((Long) o1, (Long) o2);
                } else if (Objects.equal(OP_MAX, op)) {
                    return Math.max((Long) o1, (Long) o2);
                }
            }
            if (o1 instanceof Double && o2 instanceof Double) {
                if (Objects.equal(OP_SUM, op)) {
                    return (Double) o1 + (Double) o2;
                } else if (Objects.equal(OP_MIN, op)) {
                    return Math.min((Double) o1, (Double) o2);
                } else if (Objects.equal(OP_MAX, op)) {
                    return Math.max((Double) o1, (Double) o2);
                }
            }
            if (o1 instanceof Float && o2 instanceof Float) {
                if (Objects.equal(OP_SUM, op)) {
                    return (Float) o1 + (Float) o2;
                } else if (Objects.equal(OP_MIN, op)) {
                    return Math.min((Float) o1, (Float) o2);
                } else if (Objects.equal(OP_MAX, op)) {
                    return Math.max((Float) o1, (Float) o2);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("object convert error");
        }
    }

    public static Map<Object, Object> aggGroup(TableData tableData, String groupColumn, String op,
            String aggColumn) throws Exception {
        if (tableData.tableEmpty()) {
            return Collections.emptyMap();
        }
        List<List<Object>> rows = tableData.getRows();
        int groupColumnIndex = tableData.getColumnIndex(groupColumn);
        int aggColumnIndex = tableData.getColumnIndex(aggColumn);
        Map<Object, Object> result = new HashMap<>();
        for (List<Object> row : rows) {
            Object key = row.get(groupColumnIndex);
            Object value = row.get(aggColumnIndex);

            result.put(key, opObject(result.get(key), value, op));
        }
        return result;
    }
}
