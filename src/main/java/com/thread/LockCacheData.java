package com.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockCacheData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(); 
    
    public void processDataCache(Object obj) {
        rwl.readLock().lock();
        
        if(!cacheValid) {
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            process(obj);
            rwl.readLock().lock();
            rwl.writeLock().unlock();
        }
        
        try {
            readData();
        }finally {
            rwl.readLock().unlock();
        }
        
    }
    
    private void readData() {
        System.out.println(data);
    }
    
    private void process(Object data) {
        //
        this.data = data; 
    }
    
}
