package com.rwt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheData {
    private static CacheData cacheInstance = new CacheData();

    private CacheData() {}

    public static CacheData getCacheData() {
        return cacheInstance;
    }

    private Map<String, Object> cache = new HashMap<>();// 缓存 -> 直接使用ConcurrentHashMap
    private final java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();

    public Object getValue(String key) {
        Object value = null;
        lock.readLock().lock();
        try {
            value = cache.get(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        if (value == null) {
            lock.writeLock().lock();
            try {
                // 从db获取数据
                value = getValueDB(key);
                cache.put(key, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }

        return value;
    }
    // simulation db
    private Object getValueDB(String key) {
        return 1;
    }

    public static void main(String[] args) {
        getCacheData().getValue("1");
    }
}
