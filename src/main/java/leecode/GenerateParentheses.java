package leecode;

import java.util.ArrayList;
import java.util.List;

// 22. 括号生成
// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
public class GenerateParentheses {
    List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return result;
    }
    
    private void dfs(int left, int right, String str) {
        if(left == 0 && right == 0) {
            result.add(str);
            return;
        }
        
        if(left > 0) {
            dfs(left-1, right, str+"(");
        }
        
        if(right > left) {
            dfs(left, right-1, str+")");
        }
    }
    
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
//        System.out.println(gp.generateParenthesis(1));
        System.out.println();
        System.out.println(gp.generateParenthesis(2));
    }
}
