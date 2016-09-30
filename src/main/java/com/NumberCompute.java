package com;

import java.math.BigDecimal;

public class NumberCompute {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(998.01);
		BigDecimal b = new BigDecimal("100");
		System.out.println(a.multiply(b));

		BigDecimal aa = new BigDecimal(135.95);
		BigDecimal bb = new BigDecimal("100");
		System.out.println(aa.multiply(bb));

		BigDecimal test = new BigDecimal(4.015);
		BigDecimal test1 = new BigDecimal(100);
		System.out.println(test.multiply(test1));
		
		
		BigDecimal as = new BigDecimal("998.01");
		BigDecimal bs = new BigDecimal("100");
		System.out.println(as.multiply(bs));
	}
}
