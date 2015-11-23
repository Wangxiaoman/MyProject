package com.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MutiSum {
	public static void main(String[] args) throws Exception {
		List<File> files = new ArrayList<File>();
		files.add(new File("/Users/xiaoman/datas/1.txt"));
		files.add(new File("/Users/xiaoman/datas/2.txt"));
		files.add(new File("/Users/xiaoman/datas/3.txt"));
		int sum = 0;
		
//		List<FutureTask<Integer>> list = new ArrayList<FutureTask<Integer>>();
//		
//		for(File file : files){
//			FutureTask<Integer> task = new FutureTask<Integer>(new Sum(file));
//			list.add(task);
//			new Thread(task).start();
//		}
//		
//		for(FutureTask<Integer> future : list){
//			try {
//				sum = sum + future.get();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		//2、executors
		ExecutorService es = Executors.newFixedThreadPool(3);
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es);
		for(File file : files){
			cs.submit(new Sum(file));
		}
		for(int i=0;i<files.size();i++){
			sum  = sum + cs.take().get();
		}
		
		System.out.println("sum:"+ sum);
		es.shutdown();
	}
}

class Sum implements Callable<Integer>{
	
	private File file;
	private BufferedReader br;
	
	public Sum(File file){
		this.file = file;
	}
	
	private List<Integer> getFileData(File file){
		List<Integer> list = new ArrayList<Integer>();
		try {
			br = new BufferedReader(new FileReader(file));
			String str;
			while((str = br.readLine()) != null){
				int temp = Integer.valueOf(str);
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		List<Integer> data = getFileData(file);
		
		System.out.println("thread - call begin file"+file.getName());
		Thread.sleep(1000);
		
		for(int d : data){
			sum = sum + d;
		}
		System.out.println("thread - call end file"+file.getName());
		return sum;
	}
	
}
