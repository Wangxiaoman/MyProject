package qlm;
/**
 * 第2333个能被2或者被3整除的正整数
 * @author xiaoman
 *
 */
public class OneProblem {
 
  public static int getTheNumber(int nth){
    int beginNumber = 1;
    do{
      beginNumber++;
      if(beginNumber % 2 == 0 || beginNumber % 3 == 0){
        nth--;
      }
    }while(nth > 0);
    return beginNumber;
  }
  
  public static void main(String[] args) {
    System.out.println(getTheNumber(1));
    System.out.println(getTheNumber(5));
    System.out.println(getTheNumber(10));
    System.out.println(getTheNumber(2333));
  }
}
