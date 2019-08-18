/**
 * @description: 反转链表
 * @author: Daniel
 * @create: 2019-03-03-08-28
 **/
public class ReverseList_24 {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head, first = null;
        while(cur != null) {
            ListNode temp =  cur.next;
            cur.next = first;
            first = cur;
            cur = temp;
        }
        return first;
    }

    public static void main(String[] args) {
        ReverseList_24 reverseList_24 = new ReverseList_24();
        reverseList_24.test1();
    }

    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode head = reverseList(node1);

        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
