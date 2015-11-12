package com.thread;

import java.util.LinkedList;
import java.util.List;

//common await notify
public class StorageCommon implements Storage{
	private static final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();
	
	
	public void produce(int num){
		synchronized (list) {
			while(this.list.size() + num > MAX_SIZE){
				System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!"); 
				
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0;i<num;i++){
				list.add(new Object());
			}
			
			System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
			
			list.notifyAll();
		}
	}
	
	
	public void comsume(int num){
		synchronized (list) {
			while(this.list.size() < num){
				System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!");  
				
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0;i<num;i++){
				list.remove();
			}
			System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());  
			
			list.notifyAll();
			
		}
	}


	public static int getMaxSize() {
		return MAX_SIZE;
	}


	public List<Object> getList() {
		return list;
	}
	
	
}
