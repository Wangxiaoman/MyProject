package leecode;

import java.util.Arrays;
import java.util.Comparator;


public class TwoNumber {
	public static int[] twoSum(int[] numbers, int target) {
		int length = numbers.length;
		
        int begin = 0;
        int end = length-1;
        
        Node[] n = new Node[length];
        for(int i=0;i<length;i++){
        	n[i] = new Node(numbers[i],i+1);
        }
        
        
        Arrays.sort(n, new Comparator<Node>(){
			@Override
			public int compare(Node o1, Node o2) {
				return o1.getVal()-o2.getVal();
			}
        });
        
        while(begin < end){
            int sum = n[begin].getVal()+n[end].getVal();
            if(sum==target){
            	int[] ret = new int[2];
            	ret[0] = Math.min( n[begin].getIndex(), n[end].getIndex());
                ret[1] = Math.max( n[begin].getIndex(), n[end].getIndex());
            	return ret;
            }else if(sum<target)
            	++begin;
            else if(sum>target)
            	--end;
        }
        return new int[]{0,0};
    }
	
	public static void main(String[] args) {
		int[] numbers = {5,75,25};
		int targer = 100;
		
		int[] result = twoSum(numbers,targer); 
		System.out.println(result[0]+":"+result[1]);
	}
}

class Node{
    private int val;
    private int index;

    Node(int v, int idx) {
    	this.val = v;
    	this.index = idx;
    }
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Node [val=" + val + ", index=" + index + "]";
	}
}
