package leecode;

public class NodeSumOfNumbersSolution {
    /**
     * @param node1: The first ListNode
     * @param node2: The second ListNode
     * @return: A new linked list representing the sum of corresponding numbers in the two given
     *          linked lists.
     */
    public static ListNode sumOfNumbers(ListNode node1, ListNode node2) {
        // --- write your code here ---
        ListNode result = null;
        ListNode tmp = null;
        int input = 0;
        while (true) {
            int n1 = 0;
            int n2 = 0;
            if (node1 != null) {
                n1 = node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                n2 = node2.val;
                node2 = node2.next;
            }

            int num = n1 + n2 + input;
            input = num / 10;
            if (result == null) {
                result = new ListNode(num % 10);
                tmp = result;
            } else {
                tmp.next = new ListNode(num % 10);
                tmp = tmp.next;
            }

            if (node1 == null && node2 == null) {
                if (input != 0) {
                    tmp.next = new ListNode(input);
                    return result;
                } else {
                    return result;
                }
            }
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    
    public static int peakIndexInMountainArray(int[] a) {
        // Write your code here
        return peakIndexInMountainArray(a, 0, a.length);
    }

    public static int peakIndexInMountainArray(int[] a, int b, int e) {
        int m = (b+e)/2;
        System.out.println("m:"+m);
        if(a[m] > a[m-1] && a[m] > a[m+1]){
            return m;
        } 
        if(a[m] < a[m-1]){
            return peakIndexInMountainArray(a, b, m);
        }
        if(a[m] < a[m+1]){
            return peakIndexInMountainArray(a, m, e);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = new int[] {0,2,3,2,1,0};
        System.out.println(peakIndexInMountainArray(a));
        
        
//        ListNode l1 = new ListNode(2);
//        ListNode l2 = new ListNode(4);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;
//
//        ListNode ll1 = new ListNode(5);
//        ListNode ll2 = new ListNode(6);
//        ListNode ll3 = new ListNode(4);
//        ll1.next = ll2;
//        ll2.next = ll3;
//
//        ListNode l = sumOfNumbers(l1, ll1);
//        while(l != null) {
//            System.out.println(l.val);
//            l = l.next;
//        }
    }
}
