package com.thread;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleExample {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    AtomicInteger threadCount = new AtomicInteger(0);

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("in");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadCount.incrementAndGet()+",beep");
            }
        };
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, SECONDS);
//        scheduler.schedule(new Runnable() {
//            public void run() {
//                beeperHandle.cancel(true);
//            }
//        }, 1 * 60, SECONDS);
    }
    
    public static void main(String[] args) {
        ScheduleExample s = new ScheduleExample();
        s.beepForAnHour();
    }
}
