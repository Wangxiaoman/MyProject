package leecode;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesRemove {
    public static String removeParentheses(String s) {
        List<Parentheses> plist = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(c == ')') {
                if(plist.size() == 0) {
                    plist.add(new Parentheses(i,')'));
                } else if(plist.get(plist.size()-1).lr == '(') {
                    plist.remove(plist.size()-1);
                } else {
                    plist.add(new Parentheses(i,')'));
                }
            } else if (c == '(') {
                plist.add(new Parentheses(i,'('));
            }
        }
        if(plist.size() == 0) {
            return s;
        }
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(plist.size() > j) {
                Parentheses p = plist.get(j);
                if(p.index == i) {
                    j++;
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private static class Parentheses{
        int index;
        Character lr;
        public Parentheses(int index, Character lr) {
            this.index = index;
            this.lr = lr;
        }
        
        @Override
        public String toString() {
            return this.index + ":" + this.lr;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(removeParentheses("a(b(c(de)fgh)"));
        System.out.println(removeParentheses("a(()"));
        System.out.println(removeParentheses("((("));
        System.out.println(removeParentheses("))"));
    }
}
