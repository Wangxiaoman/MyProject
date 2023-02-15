package com.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

    private int delayNum;
    private Runnable task;

    public DelayTask() {}
    public DelayTask(int delayNum, Runnable task) {
        this.delayNum = delayNum;
        this.task = task;
    }
    
    public Runnable getTask() {
        return this.task;
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayNum, TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long t = this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS);
        return t > 0 ? 1 : ((t == 0) ? 0 : -1);
    }

}
