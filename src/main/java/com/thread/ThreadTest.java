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
	
	public static class TestRunner implements Runnable{
		private Test test;
		private int type;
		public TestRunner(Test test,int type){
			this.test = test;
			this.type = type;
		}
		
		@Override
		public void run() {
			try {
			    String a = "test";
			    String b = new String("test");
			    System.out.println(a.hashCode());
			    System.out.println("identityHashCode a："+System.identityHashCode(a));
			    System.out.println("identityHashCode b："+System.identityHashCode(b));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(type == 1){
				test.task1();
			}else{
				test.task2();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		
		new Thread(new TestRunner(test,2)).start();
		new Thread(new TestRunner(test,1)).start();
		
		
		System.out.println(test.a);
		System.out.println(test.b);
		System.out.println(test.x);
		System.out.println(test.y);
	}
	
	
}


class Test {  
    int x = 0, y = 0;  
    int a = 0, b = 0;  
    public void task1() {  
        a = 1;  
        x = b;  
    }  
    public void task2() {  
        b = 1;  
        y = a;  
    }  
}  
