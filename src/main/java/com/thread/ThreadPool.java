package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private BlockingQueue<Runnable> queue;
	private List<PoolThread> threads = new ArrayList<PoolThread>();
	private boolean stop = false;
	
	//threadNo 池子中执行线程的数量
	//maxNo 阻塞队列最大容量
	public ThreadPool(int threadNo,int maxNo){
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(maxNo);
		for(int i=0;i<threadNo;i++){
			threads.add(new PoolThread(queue));
		}
		for(PoolThread pt : threads){
			pt.start();
		}
	}
	
	public synchronized void submit(Runnable task){
		if(stop){
			new IllegalStateException("ThreadPool is stopped");
		}
		this.queue.add(task);
	}
	
	public synchronized boolean stop(){
		this.stop = true;
		for(PoolThread pt : this.threads){
			pt.toStop();
		}
		return this.stop;
	}
	
	public static void main(String[] args) {
	    ExecutorService es = Executors.newFixedThreadPool(5);
	    for(int i=0;i<10;i++){
    		es.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("111");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
	    }
		
		
//		Executor ex1 = Executors.newCachedThreadPool();
//		Executor ex2 = Executors.newScheduledThreadPool(5);
//		Executor ex3 = Executors.newSingleThreadExecutor();
		
	}

}

/**
 * pool thread 
 * @author wangxiaoman
 *
 */
class PoolThread extends Thread{
	private BlockingQueue<Runnable> queue;
	private boolean stop=false;
	
	public PoolThread(BlockingQueue<Runnable> queue){
		this.queue = queue;
	}
	
	public void run(){
		//只要stop不为true，那么该线程从队列中拿出一个任务执行，直到线程终止
		while(!stop){
			try {
				this.queue.take().run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void toStop(){
		this.stop = true;
		this.interrupt();
	}
	
	public synchronized boolean isStoped(){
		return this.stop;
	}
}
