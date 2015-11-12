package com.thread;

public class Main {
	
	public static void main(String[] args) {
		// 仓库对象  
//	    Storage storage = new StorageCommon();
	    
//	    Storage storage = new StorageLock();
	    
	    Storage storage = new StorageQueue();

	    Producer producer1 = new Producer(100, storage);
	    Producer producer2 = new Producer(100, storage);
	    Producer producer3 = new Producer(100, storage);
	    
	    Customer c1 = new Customer(20, storage);
	    Customer c2 = new Customer(50, storage);
	    Customer c3 = new Customer(80, storage);
	    Customer c4 = new Customer(10, storage);
	    
	    new Thread(producer1).start();
	    new Thread(producer2).start();
	    new Thread(producer3).start();
	    
	    new Thread(c1).start();
	    new Thread(c2).start();
	    new Thread(c3).start();
	    new Thread(c4).start();
	}
	
}

class Producer implements Runnable{
	private int num;
	private Storage storage;
	
	public Producer(int num,Storage storage){
		this.num = num;
		this.storage = storage;
	}
	
	@Override
	public void run() {
		storage.produce(num);
	}
}

class Customer implements Runnable {
	private int num;
	private Storage storage;
	
	public Customer(int num,Storage storage){
		this.num = num;
		this.storage = storage;
	}
	
	@Override
	public void run() {
		storage.comsume(num);
	}
	
}
