package com;


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
		number(3);
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