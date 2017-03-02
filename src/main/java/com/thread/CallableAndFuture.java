package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {
    
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            futures.add(threadPool.submit(new Task(i)));
        }
        try {
            Thread.sleep(1000);// 可能做一些事情

            int allSum = 0;
            for (Future<Integer> f : futures) {
                int fsum = f.get();
                System.out.println("sum:" + fsum);
                allSum += fsum;
            }
            System.out.println("allSum:" + allSum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}


class Task implements Callable<Integer> {
    private int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        // 替换成db的查询
        int sum = 0;
        for (int j = 0; j <= i; j++) {
            sum += j;
        }
        return sum;
    }

}


