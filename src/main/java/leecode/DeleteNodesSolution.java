package leecode;

public class DeleteNodesSolution {
    /**
     * @param head: Head of a linked list.
     * @param m: Nodes to be kept.
     * @param n: Nodes to be deleted.
     * @return: The head of the modified list after removing the mentioned nodes.
     */
    public static ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode c = head;
        while(c.next != null) {
            c = opNode(true, m-1, c);
            if(c.next == null) {
                return head;
            }
            c = opNode(false, n, c);
            if(c.next == null) {
                return head;
            }
        }
        return head;
    }
    
    private static ListNode opNode(boolean skipOrdel, int num, ListNode node) {
        while(num -- > 0){
            if(node.next == null) {
                return node;
            }
            if(skipOrdel) {
                node = node.next;
            } else {
                node.next = node.next.next;
            }
        }
        return node;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode l6 = new ListNode(6);
        l5.next = l6;
        
        ListNode r = deleteNodes(l1, 2, 3);
        while(r.next != null) {
            r = r.next;
            System.out.print("r:"+r.val+",");
        }
    }
}
