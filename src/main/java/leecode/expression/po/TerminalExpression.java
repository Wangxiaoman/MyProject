package leecode.expression.po;

import leecode.expression.Expression;

public class TerminalExpression implements Expression {
    
    private String[] words;
    public TerminalExpression(String[] words) {
        this.words = words;
    }

    @Override
    public boolean interpret(String context) {
        for(String word : words) {
            if(context.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
