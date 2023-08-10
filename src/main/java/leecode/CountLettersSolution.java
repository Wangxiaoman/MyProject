package leecode;

public class CountLettersSolution {
    public static int countLetters(String s) {
        // write your code here
        int sum = 0;
        int sameCount = 1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i) != s.charAt(i-1)){
                sum += sumNum(sameCount);
                sameCount = 1;
            } else {
                sameCount ++;
            }
            if(i == s.length()-1){
                sum += sumNum(sameCount);
            }
        }
        return sum;
    }

    private static int sumNum(int num){
        int s = 0;
        for(int i=1;i<=num;i++){
            s += i;
        }
        return s;
    }
    
    public static void main(String[] args) {
        System.out.println(sumNum(2));
        System.out.println(countLetters("aabb"));
    }
    
}
