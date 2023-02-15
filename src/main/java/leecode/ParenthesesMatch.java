package leecode;

public class ParenthesesMatch {
    public static boolean matchParentheses(String string) {
        int counter = 0;
        for(int i=0;i<string.length();i++) {
            Character c = string.charAt(i);
            if(c == ')') {
                counter = counter - 1;
                if(counter < 0) {
                    return false;
                }
            } else {
                counter = counter + 1;
            }
        }
        if(counter == 0) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(matchParentheses("()"));
        System.out.println(matchParentheses(")("));
        System.out.println(matchParentheses("()()"));
        System.out.println(matchParentheses("(()"));
    }
}
