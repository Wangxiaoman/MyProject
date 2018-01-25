package com.thread.locksync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        int N = 10;
        Thread[] threads = new Thread[N];
        for (int i = 0; i < N; ++i) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get JSR166 lock!");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    lock.unlock();
                }

            });
        }
        lock.lock();
        for (int i = 0; i < N; ++i) {
            threads[i].start();
            Thread.sleep(200);
        }
        lock.unlock();

        for (int i = 0; i < N; ++i)
            threads[i].join();
    }
}
