package com.tabledemo.op;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.tabledemo.ColumnOpValue;
import com.tabledemo.SQLCondition;
import com.tabledemo.TableData;

public class SelectOperator {
    public static List<List<Object>> select(TableData tableData, SQLCondition condition) {
        if (tableData.tableEmpty()) {
            return Collections.emptyList();
        }

        if (!Objects.isNull(condition)) {
            List<ColumnOpValue> covs = condition.getCovs();
            List<List<Object>> result = tableData.getRows();
            for (ColumnOpValue cov : covs) {
                int columnIndex = tableData.getColumnIndex(cov.getColumn());
                result = filterByColumn(cov, result, columnIndex);
            }

            int limit = condition.getLimit();
            int offset = condition.getOffset();
            if ((result != null && !result.isEmpty()) && offset > 0) {
                if (result.size() > limit) {
                    return result.subList(limit, Math.min(result.size(), offset));
                }
            }
            return result;
        }

        return tableData.getRows();
    }

    private static List<List<Object>> filterByColumn(ColumnOpValue cov, List<List<Object>> rows, int columnIndex) {
        if(columnIndex >= 0) {
            return rows.stream().filter(row -> opColumn(cov, row.get(columnIndex)))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static boolean opColumn(ColumnOpValue cov, Object object) {
        if (Objects.equals(cov.getOp(), "=")) {
            return cov.getValue().equals(object);
        }
        if (object instanceof Integer) {
            if (Objects.equals(cov.getOp(), ">=")) {
                return (int) object >= (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), ">")) {
                return (int) object > (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<=")) {
                return (int) object <= (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<")) {
                return (int) object < (int) cov.getValue();
            }
        }
        if (object instanceof Integer) {
            if (Objects.equals(cov.getOp(), ">=")) {
                return (int) object >= (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), ">")) {
                return (int) object > (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<=")) {
                return (int) object <= (int) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<")) {
                return (int) object < (int) cov.getValue();
            }
        }
        if (object instanceof Long) {
            if (Objects.equals(cov.getOp(), ">=")) {
                return (Long) object >= (Long) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), ">")) {
                return (Long) object > (Long) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<=")) {
                return (Long) object <= (Long) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<")) {
                return (Long) object < (Long) cov.getValue();
            }
        }
        if (object instanceof Float) {
            if (Objects.equals(cov.getOp(), ">=")) {
                return (Float) object >= (Float) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), ">")) {
                return (Float) object > (Float) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<=")) {
                return (Float) object <= (Float) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<")) {
                return (Float) object < (Float) cov.getValue();
            }
        }
        if (object instanceof Double) {
            if (Objects.equals(cov.getOp(), ">=")) {
                return (Double) object >= (Double) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), ">")) {
                return (Double) object > (Double) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<=")) {
                return (Double) object <= (Double) cov.getValue();
            }
            if (Objects.equals(cov.getOp(), "<")) {
                return (Double) object < (Double) cov.getValue();
            }
        }
        return false;
    }
}
