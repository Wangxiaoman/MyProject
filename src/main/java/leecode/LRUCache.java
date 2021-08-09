package leecode;

import java.util.LinkedHashMap;
import java.util.Map;

// 146. LRU 缓存机制

public class LRUCache {
    
    private LinkedHashMap<Integer, Integer> cache;
    public LRUCache(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
                return cache.size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
        System.out.println(cache.size());
    }
    
    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1, 1);
        c.put(2, 1);
        System.out.println(c.get(1));
        c.put(1, 1);
        System.out.println(c.get(1));
        c.put(4, 1);
        c.put(5, 1);
        System.out.println(c.get(1));
        c.put(6, 1);
        System.out.println(c.get(4));
    }
}
