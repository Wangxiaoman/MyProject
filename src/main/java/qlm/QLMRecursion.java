package qlm;

/**
 * 
* @ClassName: QLMRecursion 
* @Description: TODO
* @author xiaoman 
* @date 2016年5月8日 下午5:20:11 
*
* function f(m,n)
{
    if(m==0)return n+1;
    if(n==0)return f(m-1,1);
    return f(m-1,f(m,n-1));
}

f(7,7)的16进制表达的最后8位

*
 */
public class QLMRecursion {

  public static int getResult(int m,int n){
    if(m == 0) return n+1;
    if(n == 0) return getResult(m-1,1);
    return getResult(m-1,getResult(m,n-1));
  }
  
  
  public static void main(String[] args) {
    System.out.println(getResult(1,1));
    System.out.println(getResult(2,2));
    System.out.println(getResult(3,3));
    System.out.println(getResult(3,4));
  }
}
