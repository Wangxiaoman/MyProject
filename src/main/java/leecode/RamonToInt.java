package leecode;

//罗马数字 -> 阿拉伯数字
//如果是左面的数字比右边小，那么需要减去；反之，则加上，比如 VI -> 6,IV -> 4

public class RamonToInt {
	public class Solution {
	    public int romanToInt(String s) {
	        int positive = 0;
	        int negative = 0;
	        for(int i=0;i<s.length();i++){
	            int current = singleLetterValue(s.charAt(i));
	            if(i == s.length()-1 || current >= singleLetterValue(s.charAt(i+1))){
	                positive += current;
	            }else{
	                negative += current;
	            }
	        }
	        return positive - negative;
	    }
	    
	    public int singleLetterValue(char c)
	    {
	        switch(c)
	        {
	            case 'I':
	                return 1;
	            case 'V':
	                return 5;
	            case 'X':
	                return 10;
	            case 'L':
	                return 50;
	            case 'C':
	                return 100;
	            case 'D':
	                return 500;
	            case 'M':
	                return 1000;
	        }
	        return -1;
	    }
	}

	public static void main(String[] args) {
		RamonToInt rt  = new RamonToInt();
		RamonToInt.Solution sl = rt.new Solution();
		
		System.out.println(sl.romanToInt("VII")); 
		System.out.println(sl.romanToInt("DIV")); 
		
		System.out.println(2/10);
	}
}
