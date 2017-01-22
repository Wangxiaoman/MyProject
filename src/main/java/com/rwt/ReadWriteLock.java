package com.rwt;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLock {
    private Map<Thread,Integer> readMap = new HashMap<>();
    private int writes = 0;
    private int waitWrites = 0;
    private Thread writeTread = null;
    
    // 写的级别更高，等待写也高于读
    public synchronized void lockRead() throws InterruptedException{
        Thread thread = Thread.currentThread();
        while(!canGrantReadAccess(thread)){
            wait();
        }
        putRead(thread);
    }
    
    public synchronized void unlockRead(){
        Thread thread = Thread.currentThread();
        removeRead(thread);
        notifyAll();
    }
    
    // 只要资源空闲，等写的线程竞争获取资源
    public synchronized void lockWirte() throws InterruptedException{
        waitWrites++;
        Thread thread = Thread.currentThread();
        while(!canGrantWriteAccess(thread)){
            wait();
        }
        writeTread = thread;
        waitWrites--;
        writes++;
    } 

    public synchronized void unlockWrite(){
        if(writes > 0){
            writes--;
            if(writes == 0){
                writeTread = null;
            }
        }
        notifyAll();
    }
    
    private boolean isOnlyReader(Thread thread){
        return readMap.size()==1 && readMap.get(thread)!=null;
    }
    
    private boolean canGrantWriteAccess(Thread thread){
        // 读锁升级到写锁，如果只有一个读锁，那么可以升级到写锁
        if(isOnlyReader(thread)) return true;
        if(readMap.size() > 0) return false;
        if(writeTread == null) return true;
        if(thread != writeTread) return false;
        return true;
    }
    
    private boolean canGrantReadAccess(Thread thread){
        // 写锁降级为读锁
        if(isWriter(thread)) return true;
        if(writes > 0) return false;
        if(readMap.containsKey(thread)) return true;//权限高于等待写的线程
        if(waitWrites > 0) return false;
        return true;
    }
    
    private boolean isWriter(Thread thread){
        return thread==writeTread;
    }
    
    private void putRead(Thread thread){
        if(readMap.containsKey(thread)){
            readMap.put(thread, readMap.get(thread)+1);
        }
        readMap.put(thread, 1);
    }
    private void removeRead(Thread thread){
        if(readMap.containsKey(thread)){
            if(readMap.get(thread) > 1){
                readMap.put(thread, readMap.get(thread)-1);
            }else{
                readMap.remove(thread);
            }
        }
    }
}
