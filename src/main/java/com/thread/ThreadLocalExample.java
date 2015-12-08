package com.thread;

public class ThreadLocalExample {


    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
               new InheritableThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set( (int) (Math.random() * 100D) );
    
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            
            System.out.println(threadLocal.get());
        }
    }

    //子线程有全权限访问主线程的InheritableThreadLocal
    static final ThreadLocal<String> local = new InheritableThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
        
        
        local.set("abac");
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(local.get());
				local.set("abacsdfaf");
				System.out.println(local.get());
			}
		}).start();
        
    }

}
