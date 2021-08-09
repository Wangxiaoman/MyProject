package leecode;

// 23. 合并K个升序链表
public class MergeKSortedLists {
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

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode current = null;
        ListNode result = current;
        int[] indexList = new int[lists.length];
        int counter = lists.length;
        do {
            if (counter == 0) {
                return result;
            }

            Integer temp = null;
            for (int i = 0; i < indexList.length; i++) {
                ListNode node = lists[i];
                if (node == null) {
                    continue;
                } else {
                    if (node.next == null) {
                        counter--;
                    }
                    if (temp == null || node.val < temp) {
                        temp = node.val;
                        node = node.next;
                    }
                }

                if (current == null) {
                    current = new ListNode(temp);
                } else {
                    current.next = new ListNode(temp);
                    current = current.next;
                }
            }
        } while (true);
    }

    public static ListNode mergeList(ListNode[] lists, int b, int e) {
        if (b > e) {
            return null;
        }
        if (b == e) {
            return lists[b];
        }
        int mid = (e + b) / 2;
        return merge2(mergeList(lists, b, mid), mergeList(lists, mid + 1, e));
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
        } else {
            l2.next = merge(l2.next, l1);
        }
        return l1.val < l2.val ? l1 : l2;
    }

    public static ListNode merge2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode result = null;
        ListNode head = null;
        while (true) {
            if (result == null) {
                if (l1.val < l2.val) {
                    result = l1;
                    l1 = l1.next;
                } else {
                    result = l2;
                    l2 = l2.next;
                }
                head = result;
            }
            if (l1 == null) {
                result.next = l2;
                return head;
            }
            if (l2 == null) {
                result.next = l1;
                return head;
            }

            if (l1.val < l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;

        ListNode ll1 = new ListNode(1);
        ListNode ll2 = new ListNode(4);
        ListNode ll3 = new ListNode(7);
        ll1.next = ll2;
        ll2.next = ll3;

        ListNode lll1 = new ListNode(3);
        ListNode lll2 = new ListNode(4);
        ListNode lll3 = new ListNode(8);
        lll1.next = lll2;
        lll2.next = lll3;

        ListNode[] list = new ListNode[3];
        list[0] = l1;
        list[1] = ll1;
        list[2] = lll1;
        ListNode l = mergeList(list, 0, 2);

        // ListNode l = merge(l1, ll1);
        // ListNode ll = merge2(l1, ll1);
         while (l != null) {
         System.out.print(l.val + ",");
         l = l.next;
         }
        // System.out.println();
        // while (ll != null) {
        // System.out.print(ll.val + ",");
        // ll = ll.next;
        // }
    }
}
