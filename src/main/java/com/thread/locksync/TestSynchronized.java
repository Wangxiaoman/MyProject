package com.thread.locksync;

public class TestSynchronized {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        Thread[] threads = new Thread[N];
        for (int i = 0; i < N; ++i) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + " get synch lock!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            });
        }
        
        synchronized (lock) {
            for (int i = 0; i < N; ++i) {
                threads[i].start();
                Thread.sleep(200);
            }
        }

        for (int i = 0; i < N; ++i)
            threads[i].join();
    }
}
