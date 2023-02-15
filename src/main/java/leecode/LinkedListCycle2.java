package leecode;

// 142. 环形链表 II
public class LinkedListCycle2 {
    public static ListNode detectCycle(ListNode head) {
        ListNode l1 = head;
        ListNode l2 = head;
        while(true) {
            if(l2.next == null || l2.next.next == null) {
                return null;
            }
            l1 = l1.next;
            l2 = l2.next.next;
            if(l1 == l2) {
                break;
            }
        }
        
        ListNode h = head;
        while(h != l1) {
            h = h.next;
            l1 = l1.next;
        }
        return h;
    }

    public static void main(String[] args) {
        
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


