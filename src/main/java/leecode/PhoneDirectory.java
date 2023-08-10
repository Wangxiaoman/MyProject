package leecode;

import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory {
    private List<Integer> nums;
    private int currentNum = 0;
    
    public PhoneDirectory(int maxNumbers) {
        this.nums = new ArrayList<>();
        for(int i=0;i<maxNumbers;i++) {
            nums.add(1);
        }
    }

    /**
     * @return: the available number of the phone directory 
     */
    public int get() {
        while(currentNum < nums.size()) {
            if(nums.get(currentNum) == 1) {
                int tmp = currentNum;
                nums.set(currentNum, 0);
                currentNum++;
                return tmp;
            } else {
                currentNum++;
            }
        }
        return -1;
    }

    /**
     * @param number: the number to be checked
     * @return: check whether the number of the phone directory is available
     */
    public boolean check(int number) {
        if(number >= nums.size()) {
            return false;
        }
        return nums.get(number) == 1;
    }

    /**
     * @param number: the number of the phone directory to be released
     * @return: nothing
     */
    public void release(int number) {
        if(number < nums.size()) {
            nums.set(number, 1);
            if(currentNum > number) {
                currentNum = number;
            }
        }
    }
    
    public static void main(String[] args) {
        PhoneDirectory pd = new PhoneDirectory(3);
        System.out.println(pd.get());
        System.out.println(pd.check(0));
        System.out.println(pd.get());
        System.out.println(pd.get());
        pd.release(2);
        System.out.println(pd.check(2));
        System.out.println(pd.get());
        System.out.println(pd.check(2));;
    }
}
