package com.thread;

import java.util.concurrent.LinkedBlockingQueue;

//Lock
public class StorageQueue implements Storage{
	private static final int MAX_SIZE = 100;
	
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>();
	
	
	public void produce(int num){
		
		if(list.size() == MAX_SIZE){
			System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!"); 
		}
		
		for(int i=0;i<num;i++){
			try {
				list.put(new Object());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("【现仓储量为】:" + list.size());  	
	}
	
	
	public void comsume(int num){
		
		if(list.size() == 0){
			System.out.println("【库存量】:" + 0 + "/t暂时不能执行消费任务!"); 
		}
		
		for(int i=0;i<num;i++){
			try {
				list.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("【现仓储量为】:" + list.size());  
	}


	public static int getMaxSize() {
		return MAX_SIZE;
	}


	public LinkedBlockingQueue<Object> getList() {
		return list;
	}

}
