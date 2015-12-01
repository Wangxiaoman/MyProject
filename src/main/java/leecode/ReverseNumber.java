package leecode;


public class ReverseNumber {
	public static int reverse(int x) {
		if(x <= Integer.MIN_VALUE){
			return 0;
		}
       int absx = Math.abs(x);
       String xstr = new StringBuffer(String.valueOf(absx)).reverse().toString();
    	if(Long.valueOf(xstr)>Integer.MAX_VALUE){
    	    return 0;
        }
       if(x >= 0){
        	 return Integer.valueOf(xstr);
        }else{
        	return Integer.valueOf("-"+xstr);
        }
    }
	
	public static void main(String[] args) {
//		System.out.println(Integer.valueOf(new StringBuffer(123).reverse().toString()));
		
//		System.out.println(Integer.MAX_VALUE);
		
//		System.out.println(reverse(-2147483648));
//		System.out.println(reverse(-1230));
		
		System.out.println(Math.abs(-2147483648));
	}
}

