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
    
    public synchronized void c(){
        System.out.println("c---123");
    }
    
    public static void main(String[] args) throws Exception {
        ClassBlockingTest cb = new ClassBlockingTest();
        cb.b();
        ClassBlockingTest.a();
        cb.c();
    }
}
