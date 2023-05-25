package leecode.expression;

import leecode.expression.po.AndExpression;
import leecode.expression.po.OrExpression;
import leecode.expression.po.TerminalExpression;

/**
 * @author xiaomanwang 
 * 规则定义有两种人群是可以免费乘车的： 
 *  孕妇、儿童、残疾人、军人 人群； 
 *  城市为 杭州 或 绍兴，且类别是 老人 或 学生，并持有 市民卡 或 交通卡的人群。
 * 输入为：城市, 类别, 乘车卡(可选)
 */
public class InterpreterPattern {
    private String[] citys = {"杭州", "绍兴"};
    private String[] local = {"老人", "学生"};
    private String[] card = {"市民卡", "交通卡"};
    private String[] global = {"孕妇", "儿童", "残疾人", "军人"};
    private String s1 = "本次乘车免费~";
    private String s2 = "请付费！";
    private Expression free;

    public String freeRide(String passenger) {
        return free.interpret(passenger) ? s1 : s2;
    }

    // write your code here
    public InterpreterPattern() {
        free = new OrExpression(new TerminalExpression(global), new AndExpression(
                new TerminalExpression(citys),
                new AndExpression(new TerminalExpression(local), new TerminalExpression(card))));
    }
    
    public static void main(String[] args) {
        
    }

}
