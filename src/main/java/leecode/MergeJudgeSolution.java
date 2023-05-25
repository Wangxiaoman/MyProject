package leecode;
/**
 * @author xiaomanwang
 * 现在给你两个字符串区间（按字典顺序），请你判断两个区间是否可以合并。
    字符串区间 [a, b)，包括所有以 a 开头的字符串。
    例如：
    区间 [a,b) 和区间 [b, c] 是可以合并的
    区间 [a, b) 和区间 [ab,c) 也是可以合并的
    若是可以合并请返回 true, 不可以请返回 false
 */

public class MergeJudgeSolution {
    /**
     * @param interval_A: a string represent a interval.
     * @param interval_B: a string represent a interval.
     * @return: if two intervals can merge return true, otherwise false.
     */
    public static boolean mergeJudge(String interval_A, String interval_B) {
        // write your code here
        char ab = interval_A.charAt(0);
        char ae = interval_A.charAt(interval_A.length()-1);
        interval_A = interval_A.substring(1, interval_A.length() -1);
        String[] aas = interval_A.split(",");
        
        char bb = interval_B.charAt(0);
        char be = interval_B.charAt(interval_B.length()-1);
        interval_B = interval_B.substring(1, interval_B.length() -1);
        String[] bbs = interval_B.split(",");
        
        if(bbs[0].compareTo(aas[1]) == 0){
            if(ae == ']' || bb == '[') {
                return true;
            } else {
                return false;
            }
        }
        if(aas[0].compareTo(bbs[1]) == 0){
            if(ab == '[' || be == ']') {
                return true;
            } else {
                return false;
            }
        }
        
        // ['a','b'] ['ba','c']
        // [a, bb) [bba, cd]
        if(bbs[0].startsWith(aas[1]) && bbs[0].subSequence(aas[1].length(), bbs[0].length()).equals("a") && ae==']' && bb =='[') {
            return true;
        }
        //['a','ba'] ('b','c']
        if(aas[1].startsWith(bbs[0]) && aas[1].subSequence(bbs[0].length(), aas[1].length()).equals("a") && bb=='[' && ae ==']') {
            return true;
        }
        if(aas[0].startsWith(bbs[1]) && aas[0].subSequence(bbs[1].length(), aas[0].length()).equals("a") && be==']' && ab == '[') {
            return true;
        }
        if(bbs[1].startsWith(aas[0]) && bbs[1].subSequence(aas[0].length(), bbs[1].length()).equals("a") && ab=='[' && be == ']') {
            return true;
        }
     
        if(bbs[0].compareTo(aas[0]) > 0 && bbs[0].compareTo(aas[1]) < 0){
            return true;
        }
        if(bbs[1].compareTo(aas[0]) > 0 && bbs[1].compareTo(aas[1]) < 0) {
            return true;
        }
        if(aas[0].compareTo(bbs[0]) > 0 && aas[0].compareTo(bbs[1]) < 0){
            return true;
        }
        if(aas[1].compareTo(bbs[0]) > 0 && aas[1].compareTo(bbs[1]) < 0) {
            return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
//        System.out.println(mergeJudge("[a,b]", "[b,c]"));
//        System.out.println(mergeJudge("[a,b]", "(b,c]"));
//        System.out.println(mergeJudge("[a,b)", "(b,c]"));
//        System.out.println(mergeJudge("[a,b)", "[ab,c)"));
//        System.out.println(mergeJudge("(b,ccccccccccccc)", "(aaaaaaaaaaa,bbbbbbbbbbbbbbbb)"));
//        System.out.println(mergeJudge("(a,ab]", "[aba,c]"));
//        System.out.println(
//                mergeJudge("(kkkkkkkkkkkkkkk,yoankxciokjadkjnc)", "[aasdfasdcasdf,casdf]"));
//        System.out.println(mergeJudge("[a,asdfqwe)", "[asdfqwea,c]"));
    
//        System.out.println(mergeJudge("[abcdfaefasdczsrthwrtgapsodmc,abcdfaefasdczsrthwrtgapsodmd]", "[abcdfaefasdczsrthwrtgapsodm,abcdfaefasz]"));
        System.out.println(mergeJudge("[a,asdfqwe]","(asdfqwea,c]"));
        System.out.println(mergeJudge("[a,asdfqwe)", "[asdfqwea,c]"));
    }
}
