/**
 * 
 */
package leecode;

import java.util.Stack;

/**
 * @author linkedme
 * 将一个链表，倒排。注意将尾部节点的next置位null
 *
 */
public class ReverseList {

    public static RListNode reverseList(RListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        Stack<RListNode> s = new Stack<>();
        RListNode temp = null;
        do{
            temp =  head;
            s.add(temp);
            head = head.next;
        }while(head != null);
        
        System.out.println("s:"+s);
        
        RListNode node = s.pop();
        RListNode result = node;
        
        while(!s.isEmpty()){
            node.next = s.pop();
            node = node.next;
        }
        node.next = null;
        return result;
    }
    
    public static void main(String[] args) {
        RListNode head = new RListNode(1);
        RListNode node = new RListNode(2);
        head.next = node;
        node.next = null;
        
        RListNode result = reverseList(head);
        RListNode cur = result;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}


// Definition for singly-linked list.
class RListNode {
    int val;
    RListNode next;

    RListNode(int x) {
        val = x;
    }
}
