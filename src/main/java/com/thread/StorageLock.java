package com.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Lock
public class StorageLock implements Storage{
	private static final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();
	
	private final Lock lock = new ReentrantLock();
	
	private final Condition full = lock.newCondition();
	
	private final Condition empty = lock.newCondition();
	
	
	public void produce(int num){
		lock.lock();
		
		while(this.list.size() + num > MAX_SIZE){
			System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!"); 
			
			try {
				full.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<num;i++){
			list.add(new Object());
		}
		
		System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
		
		full.signalAll();
		empty.signalAll();
		
		lock.unlock();
			
	}
	
	
	public void comsume(int num){
		
		lock.lock();
		
		while(this.list.size() < num){
			System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行消费任务!");  
			
			try {
				empty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<num;i++){
			list.remove();
		}
		
		empty.signalAll();
		full.signalAll();
		
		System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());
		
		lock.unlock();
	}


	public static int getMaxSize() {
		return MAX_SIZE;
	}


	public List<Object> getList() {
		return list;
	}
	
	
}
