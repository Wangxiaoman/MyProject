package com.thread;

public class ClassBlockingTest {
    
    public static synchronized void a()throws Exception{
        Thread.sleep(2000);
        System.out.println("a--123");
    }
    
    public synchronized void b() throws Exception{
        Thread.sleep(2000);
        System.out.println("b--123");
    }
    
    public synchronized void c() throws Exception{
        Thread.sleep(2000);
        System.out.println("c---123");
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        ClassBlockingTest cb = new ClassBlockingTest();
        Thread t1 = new Thread(new TestThread1(cb));
        Thread t2 = new Thread(new TestThread2(cb));
        Thread t3 = new Thread(new TestThread3());
        
        t1.start();
        t2.start();
        t3.start();
    }
    
}

class TestThread1 implements Runnable{
    private ClassBlockingTest cbt;
    public TestThread1(ClassBlockingTest cbt){
        this.cbt = cbt;
    }

    @Override
    public void run() {
        try {
            cbt.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TestThread2 implements Runnable{
    private ClassBlockingTest cbt;
    public TestThread2(ClassBlockingTest cbt){
        this.cbt = cbt;
    }

    @Override
    public void run() {
        try {
            cbt.c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TestThread3 implements Runnable{
    @Override
    public void run() {
        try {
            ClassBlockingTest.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
