/**
 * 
 */
package leecode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author linkedme
 *
 */
public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private LinkedList<Integer> sortList = new LinkedList<>();
    private int index = 0;
    
    public MinStack() {
        
    }
    
    public void push(int x) {
        index ++ ;
        stack.push(x);
        
    }
    
    private void pushList(int x, int middle){
        if(sortList.isEmpty()){
            sortList.add(x);
        }else{
            if(x == sortList.get(middle)){
                sortList.add(middle+1, x);
            }
        }
    }
    
    public void pop() {
        index --;
        stack.pop();
        sortList.remove(index);
    }
    
    public int top() {
        return sortList.getLast();
    }
    
    public int getMin() {
        return sortList.getFirst();
    }
    
    public static void main(String[] args) {
        
    }
}
