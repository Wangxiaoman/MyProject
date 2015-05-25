package com.thread;

/**
 * 线程内存和主存
 * @author wangxiaoman
 *
 */
public class ThreadTest {
	public static volatile Integer number;
	public static boolean flag;
//	public static boolean flag; 如果不用volatile，那么线程不会停止，因为主存中的值不会同步到线程栈中
	
	public static class Runner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(flag){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runner()).start();
		Thread.sleep(100);
		number = 40;
		flag = true;
	}
}
