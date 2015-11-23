package leecode;
//回文数
public class Palindrome {
	public static boolean isPalindrome(int x) {
        if(x <0){
        	return false;
        }
        if(x>=0 && x<10){
        	return true;
        }
        
        StringBuffer sb = new StringBuffer(x);
        int length = sb.length();
        for(int i=0;i<length;i++){
        	if(sb.charAt(i) != sb.charAt(length) -i){
        		return false;
        	}
        }
        return true;
    }
	
	public static int romanToInt(String s) {
        for(int i=0;i<s.length();i++){
        	System.out.println(s.charAt(i));
            if(s.charAt(i) <48 && s.charAt(i)>57){
            	return 0;
            }
        }
        
        return Integer.valueOf(s);
    }
	
	public static void main(String[] args) {
//		System.out.println(isPalindrome(120030221));
		
//		romanToInt("azAZ19");
		System.out.println((int)('0'));
		System.out.println((int)('9'));
	}
}
