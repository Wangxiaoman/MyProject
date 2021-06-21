package leecode;

import java.util.ArrayList;
import java.util.List;

// 160. 相交链表
// [4,1,8,4,5]
// [5,6,1,8,4,5]
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        ListNode headA1 = new ListNode(1);
        ListNode headA2 = new ListNode(8);
        ListNode headA3 = new ListNode(4);
        ListNode headA4 = new ListNode(5);
        headA.next = headA1;
        headA1.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;

        ListNode headB = new ListNode(5);
        ListNode headB1 = new ListNode(6);
        ListNode headB2 = new ListNode(1);
        ListNode headB3 = new ListNode(8);
        ListNode headB4 = new ListNode(4);
        ListNode headB5 = new ListNode(5);
        headB.next = headB1;
        headB1.next = headB2;
        headB2.next = headB3;
        headB3.next = headB4;
        headB4.next = headB5;

        ListNode l = getIntersectionNode(headA, headB);
        System.out.println(l.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode na = headA, nb = headB;
        while (na != nb) {
            nb = nb == null ? na : nb.next;
            na = na == null ? nb : na.next;
        }
        
        return na;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
