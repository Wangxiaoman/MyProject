package com.mysql.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Connections {
	
	
	
	
	public Connection getConnection(String driver,String url,String user,String password){
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void main(String[] args) {
		
		
		Integer a = new Integer(1234);
		List<Integer> ll = new ArrayList<Integer>();
		ll.add(a);
		ll.add(a);
		ll.add(a);
		
		System.out.println("ll 1: ");
		for(Integer s : ll){
			System.out.print(s+",");
		}
		System.out.println();
		
		a = new Integer(1234567);
		
		System.out.println("ll 2: ");
		for(Integer s : ll){
			System.out.print(s+",");
		}
		System.out.println();
		
		
		A aa = new A(88888);
		List<A> ll1 = new ArrayList<A>();
		ll1.add(aa);
		ll1.add(aa);
		ll1.add(aa);
		System.out.println("ll a 1: ");
		for(A a1 : ll1){
			System.out.print(a1+",");
		}
		System.out.println();
		aa.setA(99999);;
		System.out.println("ll a 2: ");
		for(A a1 : ll1){
			System.out.print(a1+",");
		}
		System.out.println();
		
	}
}

class A{
	public A(int a){
		this.a = a;
	}
	private int a;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String toString(){
		return ""+this.a;
	}
}
