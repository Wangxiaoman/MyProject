package com.delay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

public class DelayQueueWorker {
    private static DelayQueue<DelayPriorityTask> dQueue = new DelayQueue<>();

    public boolean pushTask(int delaySecond, Runnable task, int priority) {
        DelayPriorityTask delayTask = new DelayPriorityTask(delaySecond, task, priority);
        return dQueue.add(delayTask);
    }

    public int getTaskCount() {
        return dQueue.size();
    }


    public static void insertMinHeap(List<Integer> nums, int k, int num) {
        nums.add(num);
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            int pnum = nums.get(parent);
            if (pnum < num) {
                break;
            }
            nums.set(k, pnum);
            k = parent;
        }
        nums.set(k, num);
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(10);
        insertMinHeap(nums, 0, 1);
        System.out.println(nums);
        insertMinHeap(nums, 1, 4);
        System.out.println(nums);
        insertMinHeap(nums, 2, 2);
        System.out.println(nums);
        insertMinHeap(nums, 3, 10);
        System.out.println(nums);
        insertMinHeap(nums, 4, 5);
        System.out.println(nums);
        insertMinHeap(nums, 5, 8);
        System.out.println(nums);
        insertMinHeap(nums, 6, 8);
        System.out.println(nums);
        insertMinHeap(nums, 7, 3);
        System.out.println(nums);
    }
}
