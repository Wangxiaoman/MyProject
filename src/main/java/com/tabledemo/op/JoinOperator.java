package com.tabledemo.op;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.tabledemo.TableData;

/**
 * @author xiaomanwang 
 * 1.暂时只考虑两张表的join
 * 2.返回表t1和t2的全字段
 */
public class JoinOperator {
    public static List<List<Object>> nestedLoopJoin(TableData t1, TableData t2,
            String t1Key, String t2Key) {
        if(t1.tableEmpty() || t2.tableEmpty()) {
            return Collections.emptyList();
        }
        // 双循环
        List<List<Object>> result = new ArrayList<>();
        for(List<Object> row : t1.getRows()) {
            int t1Index = t1.getColumnIndex(t1Key);
            Object t1Value = row.get(t1Index);
            
            for(List<Object> t2row : t2.getRows()) {
                int t2Index = t2.getColumnIndex(t2Key);
                Object t2Value = t2row.get(t2Index);
                
                if(Objects.equal(t1Value, t2Value)) {
                    List<Object> rrows = new ArrayList<>();
                    rrows.addAll(row);
                    rrows.add(" ");
                    rrows.addAll(t2row);
                    result.add(rrows);
                }
            }
        }
        return result;
    }

    /**
     * @param t1
     * @param t2
     * @param t1Key
     * @param t2Key
     * @return
     * 当t1和t2中的merge key在表中都有重复的时候，这时候的处理有bug
     * 可以让t1正常累加，t2的索引，需要记录值不变的下标值，直到值发生变化，再往下++
     */
    public static List<List<Object>> sortedJoin(TableData t1, TableData t2,
            String t1Key, String t2Key) {
        if(t1.tableEmpty() || t2.tableEmpty()) {
            return Collections.emptyList();
        }
        // sorted
        List<List<Object>> result = new ArrayList<>();
        List<List<Object>> sortedT1 = OrderOperator.orderby(t1, Arrays.asList(t1Key), 0);
        List<List<Object>> sortedT2 = OrderOperator.orderby(t2, Arrays.asList(t2Key), 0);
        
        // merge
        int t1KeyIndex = t1.getColumnIndex(t1Key);
        int t2KeyIndex = t2.getColumnIndex(t2Key);
        
        int t1Index = 0;
        int t2Index = 0;
        List<Object> t1row = sortedT1.get(0);
        List<Object> t2row = sortedT2.get(0);
        Object t1Value = null;
        Object t2Value = null;
        
        do {
            t1Value = t1row.get(t1KeyIndex);
            t2Value = t2row.get(t2KeyIndex);
            boolean t1f = true;
            
            int v = OrderOperator.tableValueCompare(t1Value, t2Value);
            if(Objects.equal(v, 0)) {
                List<Object> rrows = new ArrayList<>();
                rrows.addAll(t1row);
                rrows.add(" ");
                rrows.addAll(t2row);
                result.add(rrows);
                if(t1f) {
                    t2Index++;
                    t1f = false;
                } else {
                    t1Index++;
                    t1f = true;
                }
            }else if(v > 0) {
                t2Index++;
            } else {
                t1Index++;
            }
            
            if (t2Index == t2.tableSize() || t1Index == t1.tableSize()) {
                return result;
            }
            
            if(Objects.equal(v, 0)) {
                t2row = sortedT2.get(t2Index);
                t1row = sortedT1.get(t1Index);
            } else if(v > 0) {
                t2row = sortedT2.get(t2Index);
            } else {
                t1row = sortedT1.get(t1Index);
            }
            
        } while(true);
    }

    public static List<List<Object>> hashJoin(TableData t1, TableData t2,
            String t1Key, String t2Key) {
        if(t1.tableEmpty() || t2.tableEmpty()) {
            return Collections.emptyList();
        }
        
        int t1KeyIndex = t1.getColumnIndex(t1Key);
        int t2KeyIndex = t2.getColumnIndex(t2Key);
        
        List<List<Object>> result = new ArrayList<>();
        // 构建hashtable
        Map<Object,List<List<Object>>> t1Map = new HashMap<>();
        for(List<Object> row : t1.getRows()) {
            Object v = row.get(t1KeyIndex);
            List<List<Object>> vRows = t1Map.getOrDefault(v, new ArrayList<>());
            vRows.add(row);
            t1Map.put(v, vRows);
        }
        
        // 匹配t2
        for(List<Object> row : t2.getRows()) {
            Object v = row.get(t2KeyIndex);
            List<List<Object>> t1Rows = t1Map.get(v);
            if(!t1Rows.isEmpty()) {
                for(List<Object> t1Row : t1Rows) {
                    List<Object> rrRow = new ArrayList<>();
                    rrRow.addAll(t1Row);
                    rrRow.add(" ");
                    rrRow.addAll(row);
                    result.add(rrRow);
                }
            }
        }
        
        return result;
    }
}
