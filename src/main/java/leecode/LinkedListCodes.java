package leecode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListCodes {

    public static void main(String[] args) {
//        testReverseList();
        
//        testMergeTwoList();
        
//        testCopyNodeList();
        
        testReverseNList();
    }


    
    // 剑指 Offer 35 copy random list
    // leecode 138
    public static Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        Map<Node,Node> randomMap = new HashMap<>();
        Node cur = head;
        while(cur != null) {
            randomMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        
        cur = head;
        while(cur != null) {
            randomMap.get(cur).next = randomMap.get(cur.next);
            randomMap.get(cur).random = randomMap.get(cur.random);
            cur = cur.next;
        }
        
        return randomMap.get(head);
    }
    
    public static void testCopyNodeList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        
        showList(n1);
        showList(copyNodeList(n1));
    }
    
    public static ListNode copyNodeList(ListNode head) {
        Map<ListNode,ListNode> nodeMap = new HashMap<>();
        ListNode cur = head;
        while(cur != null) {
            nodeMap.put(cur, new ListNode(cur.val));
            cur = cur.next;
        }
        
        cur = head;
        while(cur != null) {
            nodeMap.get(cur).next = nodeMap.get(cur.next);
            cur = cur.next;
        }
        
        return nodeMap.get(head);
    }
    
    public static void testMergeTwoList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        
        ListNode n21 = new ListNode(1);
        ListNode n22 = new ListNode(3);
        ListNode n23 = new ListNode(4);
        n21.next = n22;
        n22.next = n23;
        showList(n1);
        showList(n21);
        
        showList(mergeTwoLists(n1,n21));
    }

    // 2.1 merge-two-sorted-lists
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = null;
        
        if(l1.val <= l2.val) {
            result = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            result = new ListNode(l2.val);
            l2 = l2.next;
        }
        
        ListNode temp = result;
        while (true) {
            if(l1 == null && l2 != null) {
                temp.next = l2;
                return result;
            }
            if(l1 != null && l2 == null) {
                temp.next = l1;
                return result;
            }
            ListNode n = null;
            if(l1.val <= l2.val) {
                n = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                n = new ListNode(l2.val);
                l2 = l2.next;
            }
            temp.next = n;
            temp = temp.next;
        }
    }
    
    public static void testReverseNList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        showList(n1);
        showList(reverseN(n1, 2));
    }
    
    // 反转前N的链表
    static ListNode cer = null;
    public static ListNode reverseN(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if(n == 1) {
            cer = head.next;
            return head;
        }
        
        ListNode last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = cer;
        return last;
    }

    public static void testReverseList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        showList(n1);
        showList(reverseList(n1));
    }
    
    // 剑指 Offer 24. 反转链表
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    
    public static void showList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void showNodeList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    static class ListNode {
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
    
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}


