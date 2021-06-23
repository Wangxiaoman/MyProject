package leecode;

// 2. 两数相加
// l1 = [2,4,3], l2 = [5,6,4]
// [7,0,8]

// l1=[9,9,9,9,9,9,9] l2=[9,9,9,9]
// [8,9,9,9,0,0,0,1]
public class AddTwoNumbers {
    
    public static void main(String[] args) {
        ListNode l1 = arrayToNode(new int[] {2,4,3});
        show(l1);
        ListNode l2 = arrayToNode(new int[] {5,6,4});
        show(l2);
        ListNode result = addTwoNumbers(l1, l2);
        show(result);
        
        ListNode l3 = arrayToNode(new int[] {9,9,9,9,9,9,9});
        show(l3);
        ListNode l4 = arrayToNode(new int[] {9,9,9,9});
        show(l4);
        ListNode result2 = addTwoNumbers(l3, l4);
        show(result2);
    }
    
    private static ListNode arrayToNode(int [] arrays) {
        ListNode head = new ListNode(arrays[0]);
        ListNode temp = head;
        for (int i = 1; i < arrays.length; i++) {
            ListNode current = new ListNode(arrays[i]);
            temp.next = current;
            temp = temp.next;
        }
        return head;
    }
    
    private static void show(ListNode node) {
        while(node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
        System.out.println();
        System.out.println("----");
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int nextStep = 0;
        ListNode head = null;
        ListNode temp = null;
        ListNode pre = null;
        while(l1 != null || l2 != null) {
            int l1Num = 0;
            int l2Num = 0;
            if(l1 != null) {
                l1Num = l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                l2Num = l2.val;
                l2 = l2.next;
            }
            
            int currentNum = l1Num + l2Num + nextStep;
            if(currentNum >= 10) {
                nextStep = 1;
                currentNum = currentNum -10;
            } else {
                nextStep = 0;
            }
            
            temp = new ListNode(currentNum);
            if(head == null) {
                head = temp;
                pre = head;
            } else {
                pre.next = temp;
                pre = temp;
            }
        }
        if(nextStep == 1) {
            temp.next = new ListNode(1);
        }
        return head;
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
