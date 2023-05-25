package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author xiaomanwang Cassandra是一个NoSQL数据库（又称键值存储）。Cassandra中的一个单独数据条目由3部分构成： row_key
 *         (相当于散列密钥、分区密钥或分片密钥) column_key value
 * 
 *         row_key 用于哈希，不支持范围查询。我们将其简化为字符串。 column_key 已排序并支持范围查询。我们将其简化为整数。 value
 *         是一个字符串。你可以将任何数据序列化为字符串并将其存储在值中。
 * 
 *         现在要实现下面两个方法：
 * 
 *         insert(row_key, column_key, value) query(row_key, column_start, column_end) 返回条目列表
 *
 */

public class MiniCassandra {
    private Map<String, TreeMap<Integer, String>> dataMap = null;

    public MiniCassandra() {
        dataMap = new HashMap<String, TreeMap<Integer, String>>();
    }

    /*
     * @param raw_key: a string
     * @param column_key: An integer
     * @param column_value: a string
     * @return: nothing
     */
    public void insert(String row_key, int column_key, String value) {
        // write your code here
        TreeMap<Integer, String> rowMap = dataMap.getOrDefault(row_key, new TreeMap<>());
        rowMap.put(column_key, value);
        dataMap.put(row_key, rowMap);
    }

    /*
     * @param row_key: a string
     * @param column_start: An integer
     * @param column_end: An integer
     * @return: a list of Columns
     */
    public List<Column> query(String row_key, int column_start, int column_end) {
        TreeMap<Integer, String> rowMap = dataMap.get(row_key);
        List<Column> result = new ArrayList<>();
        if (rowMap != null && !rowMap.isEmpty()) {
            Map<Integer, String> subMap = rowMap.subMap(column_start, column_end + 1);
            if (subMap != null && !subMap.isEmpty()) {
                for (Entry<Integer, String> entry : subMap.entrySet()) {
                    Column c = new Column(entry.getKey(), entry.getValue());
                    result.add(c);
                }
            }
        }
        return result;
    }
}


class Column {
    public int key;
    public String value;

    public Column(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
