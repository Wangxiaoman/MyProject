package com.thread;

public class CounterThread extends Thread {
	protected Counter counter = null;

	public CounterThread(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			counter.add(i);
		}
	}

	public static void main(String[] args) {
		Counter counter1 = new Counter();
		Counter counter2 = new Counter();
		Thread threadA = new CounterThread(counter1);
		Thread threadB = new CounterThread(counter2);

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(counter1.count);
		System.out.println(counter2.count);
		
		Counter counter = new Counter();
		Thread thread1 = new CounterThread(counter);
		Thread thread2 = new CounterThread(counter);
		Thread thread3 = new CounterThread(counter);
		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter.count);
	}
}

class Counter {

	long count = 0;

	public synchronized void add(long value) {
		this.count += value;
	}
}
