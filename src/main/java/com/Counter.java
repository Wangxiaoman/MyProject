package com;

import java.util.ArrayList;
import java.util.List;


public class Counter {
	private volatile int a =0;
	
	private int count = 0;
	private Lock lock = new Lock();

	//1、同步块
	public int inc(){
		synchronized (this) {
			return count++;
		}
	}
	
	//2、lock
	public int incLock() throws InterruptedException{
		lock.lock();
		count ++;
		lock.unlock();
		return count;
	}
	
	public static void number(int n){
		if(n<=0)
			return ;
		number(n-1);
		number(n-1);
		System.out.println(n);
	}
	
	public static void main(String[] args) {
//		number(3);
		
		Runtime r = Runtime.getRuntime();  
		r.gc();  
		long startMem = r.freeMemory(); // 开始时的剩余内存  
		System.out.println("startMem:"+startMem);
		List ol = new ArrayList();
		for(int i=0;i<1000000;i++){
			Object o = new Object(); 
			ol.add(o);
		}
		System.out.println("lastMem:"+r.freeMemory());
		long orz = startMem - r.freeMemory(); // 剩余内存 现在 - 开始 = o 的大小 
		System.out.println("orz:"+orz);
	}

}


class Lock{
	private boolean isLocked = false;

	public synchronized void lock()
		throws InterruptedException{
		//自旋锁
		while(isLocked){
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
}