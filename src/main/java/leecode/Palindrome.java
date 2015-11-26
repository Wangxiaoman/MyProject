package leecode;
//回文数
//将这个数的回文数先算出来，然后比对这两个数
public class Palindrome {
	public static boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}
		
        int y = 0;
        int current = x;
        while(x/10 >0 ){
            y = y*10 + x%10;
            x = x/10;
        }
        
        System.out.println(y);
        y = y*10 + x;
        
        System.out.println(y);
        
        if(current == y){
            return true;
        }else{
            return false;
        }
    }
	
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(121));
	}
}
