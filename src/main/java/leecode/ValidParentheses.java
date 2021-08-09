package leecode;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;

public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(stack.size() == 0) {
                stack.add(c);
            } else {
                char last = stack.peek();
                if((last=='(' && c == ')') ||
                        (last=='[' && c == ']') ||
                                (last=='{' && c == '}')) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        
        if(stack.size() == 0) {
            return true;
        }
        return false;
    }
    
    public static boolean isValid1(String s) {
        while(s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return Objects.equal(s, StringUtils.EMPTY);
    }
    
    
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid1("([{()}])[]{}"));
        System.out.println(isValid("()]"));
    }
}
