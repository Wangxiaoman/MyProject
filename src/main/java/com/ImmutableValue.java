package com;

public class ImmutableValue {
	private int value =0;
	public ImmutableValue(int val){
		this.value = val;
	}
	public int getVal(){
		return this.value;
	}
	public ImmutableValue add(int val){
		return new ImmutableValue(this.value+val);
	}
}
