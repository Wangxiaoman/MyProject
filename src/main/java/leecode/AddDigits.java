package leecode;
/**
 * 
 * number:258
 * 
* @ClassName: AddDigits 
* @Description: 
* Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
	For example:
	Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

	Follow up:
	Could you do it without any loop/recursion in O(1) runtime?
* 
* 
* 10 11 12 13 14 15 16 17 18 19 20 21 22
* 1  2  3  4  5  6  7  8  9  1  2  3  4
* 
* 981 982 983 984 985
* 9   1   2   3   4
* 
* 
* @author xiaoman 
* @date 2015年12月1日 下午2:27:57 
*
 */
public class AddDigits {
	public static int addDigits(int num) {
        if(num < 10){
        	return num;
        }else{
        	int temp = num;
        	int sum = 0;
        	while(temp > 0){
        		sum = sum + temp%10;
        		temp = temp/10;
        	}
        	
        	if(sum >= 10){
        		return addDigits(sum);
        	}
        	return sum;
        }
        
        //return num%9==0?9:num%9;
        //return 1+(num-1)%9;
    }
	
	public static void main(String[] args) {
    	System.out.println(addDigits(888));
	}
}
