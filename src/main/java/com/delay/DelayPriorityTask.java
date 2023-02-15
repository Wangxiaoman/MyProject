package com.delay;

import java.util.concurrent.TimeUnit;

public class DelayPriorityTask extends DelayTask {

    private int priority;

    public DelayPriorityTask() {}
    public DelayPriorityTask(int delayNum, Runnable task, int priority) {
        super(delayNum, task);
        this.priority = priority;
    }
    
    public int getPriority() {
        return this.priority;
    }

    public int compareTo(DelayPriorityTask o) {
        long tp = this.priority - o.getPriority();
        if(tp == 0) {
            long t = this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS);
            return t > 0 ? 1 : ((t == 0) ? 0 : -1);
        } else {
            return tp > 0 ? 1 : -1;
        }
    }

}
