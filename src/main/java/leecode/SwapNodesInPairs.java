package leecode;

import java.util.ArrayList;
import java.util.List;

public class SwapNodesInPairs {
    
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        
        return next;
    }
    
    
    public static ListNode swap(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode n = head.next;
        head.next = swap(head.next.next);
        n.next = head;
        
        return n;
    } 

    public static ListNode swapPairs1(ListNode head) {
        ListNode temp = head;
        if (temp == null || temp.next == null){
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        int index = 0;
        while(temp != null) {
            list.add(temp);
            temp = temp.next;
            list.get(index++).next = null;
        }
        
        ListNode result = list.get(1);
        ListNode t = result;
        result.next = list.get(0);
        result = result.next;
        for (int i = 2; i < list.size(); i=i+2) {
            result.next = list.get(i+1);
            result = result.next;
            result.next = list.get(i);
        }
        
        return t;
    }
    
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode n = swapPairs(n1);
        while(n != null) {
            System.out.print(n.val + ",");
            n = n.next;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
