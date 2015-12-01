package leecode;

/**
 * 
 * @ClassName: LinkNodeRemove
 * @Description: Given linked list: 1->2->3->4->5, and n = 2. After removing the
 *               second node from the end, the linked list becomes 1->2->3->5.
 * @author xiaoman
 * @date 2015年11月27日 上午10:07:22
 * 
 */
public class LinkNodeRemove {
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		int i = 0;
		while(i++<n){
			head = head.next;
		}
		
		head = head.next.next;
		return head;
    }
	
	public static void main(String[] args) {
		ListNode l1  = new ListNode(1);
		ListNode l2  = new ListNode(2);
		ListNode l3  = new ListNode(3);
		ListNode l4  = new ListNode(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		
		System.out.println(l1.toString());
		
		System.out.println(removeNthFromEnd(l1,2).toString());
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if(this != null){
			sb.append(this.val).append("->");
			ListNode temp = this;
			do{
				temp = temp.next;
				sb.append(temp.val).append("->");
			}while(temp.next != null);
		}
		return sb.toString();
	}
}
