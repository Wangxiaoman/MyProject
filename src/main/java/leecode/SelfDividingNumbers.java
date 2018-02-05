/**
 * 
 */
package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linkedme 728
 */
public class SelfDividingNumbers {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int i=left;i<=right;i++){
            if(i<10){
                result.add(i);
                continue;
            }
            if(i % 10 == 0){
                continue;
            }
            
            int temp = i;
            boolean trueNum = true;
            while(temp > 0){
                int div = temp % 10;
                if(i % div != 0 || div == 0){
                    trueNum = false;
                    break;
                }
                temp = temp/10;
            }
            if(trueNum){
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(1,22));
    }
}
