package leecode;
/**
 * 
* @ClassName: NumberOf1Bits 
* @Description: 
* Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
* For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3
* @author xiaoman 
* @date 2015年12月19日 下午5:49:02 
*
 */
public class NumberOf1Bits {
  public static int hammingWeight(long n) {
    int count = 0;
    do{
      if(n % 2 == 1){
        count ++;
        n = n & Integer.MAX_VALUE;
      }
      n = n/2;
      
    }while(n >= 1);
    
    return count;
  }
  
  public static int hammingWeight1(long n){
    int count = 0;
    for (;n!=0;n = n & (n-1))
        count++;
    return count;
  }
  
  public static void main(String[] args) {
//    System.out.println(hammingWeight(2147483648l));
    System.out.println(hammingWeight1(2147483648l));
  }
}
