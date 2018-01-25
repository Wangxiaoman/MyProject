package com.thread.locksync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockSynchronized {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        Thread[] threads = new Thread[N];
        Thread[] threadsForWaits = new Thread[N];
        for (int i = 0; i < N; ++i) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " nowait get lock");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " nowait unlock");
                }

            });
        }
        for (int i = 0; i < N; ++i) {
            threadsForWaits[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    lock.lock(); // synchronized(lock){
                    System.out.println(Thread.currentThread().getName() + " wait first get lock");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " wait second get lock");
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

        for (int i = 0; i < N; ++i) {
            threadsForWaits[i].start();
            Thread.sleep(200);
        }
        lock.lock(); // synchronized(lock){
        for (int i = 0; i < N; ++i) {
            threads[i].start();
            Thread.sleep(200);
        }
        for (int i = 0; i < 4; ++i) {
            condition.signal();
        }
        Thread.sleep(200);
        lock.unlock();


        for (int i = 0; i < N; ++i)
            threads[i].join();

        for (int i = 0; i < N; ++i)
            threadsForWaits[i].join();

    }

}
