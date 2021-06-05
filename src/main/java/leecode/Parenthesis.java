package leecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 面试题 08.09. 括号
public class Parenthesis {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
    
    public static List<String> generateParenthesis(int n) {
        Set<String> strSet = generateParenthesis(n,0,new HashSet<>());
        return new ArrayList<>(strSet);
    }

    public static Set<String> generateParenthesis(int n, int index, Set<String> strSet) {
        if(n == index){
            return strSet;
        }
        Set<String> result = new HashSet<>();
        if(0 == index){
            result.add("()");
            return generateParenthesis(n, index +1, result);
        } else {
            for(String s : strSet){
                result.add("()"+s);
                result.add(s+"()");
                result.add("(" +s + ")");
            }
            return generateParenthesis(n,index +1,result);
        }
    }
}
