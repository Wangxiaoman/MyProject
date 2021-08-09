package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 981. 基于时间的键值存储

public class TimeMap {
    private Map<String,TreeMap<Integer,String>> map = null;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> valueMap = map.getOrDefault(key, new TreeMap<>());
        valueMap.put(timestamp, value);
        map.put(key, valueMap);
    }

    public String get(String key, int timestamp) {
        String value = "";
        TreeMap<Integer,String> valueMap = map.get(key);
        if(valueMap != null) {
            value = valueMap.getOrDefault(timestamp, "");
            if(value == "") {
                Map.Entry<Integer,String> entry = valueMap.lowerEntry(timestamp);
                if(entry != null) {
                    value = entry.getValue();
                }
            }
        }
        return value;
    }
    
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // 存储键 "foo" 和值 "bar" ，时间戳 timestamp = 1   
        System.out.println(timeMap.get("foo", 1));         // 返回 "bar"
        System.out.println(timeMap.get("foo", 3));        // 返回 "bar", 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） 。
        timeMap.set("foo", "bar2", 4); // 存储键 "foo" 和值 "bar2" ，时间戳 timestamp = 4  
        System.out.println(timeMap.get("foo", 4));        // 返回 "bar2"
        timeMap.set("foo", "bar3", 5);       // 返回 "bar2"
        System.out.println(timeMap.get("foo", 6));
    
        //["TimeMap","set","set","get","get","get","get","get"]
        // [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
        
        timeMap = new TimeMap();
        timeMap.set("love","high",10);
        timeMap.set("love","low",20);
        System.out.println(timeMap.get("love",5));
        System.out.println(timeMap.get("love",10));
        System.out.println(timeMap.get("love",20));
        System.out.println(timeMap.get("love",25));
    }
}
