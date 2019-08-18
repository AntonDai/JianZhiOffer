/**
 * @description: 链表中的倒数第k个节点
 * 两种思路：一种思路是先统计链表的长度，然后再从前往后走到第n-k+1个节点
 * 另一种思路是设置两个指针，先让第一个指针走到第k个节点，然后再让两个指针同时走，当第一个指针走到链表尾时，此时第二个指针刚好走到倒数第k个节点
 * @author: Daniel
 * @create: 2019-03-04-08-41
 **/
public class KthNodeFromEnd_22 {
    // 寻找链表中的倒数第k个节点
    public ListNode findKthToTail(ListNode head, int k) {
        if(head == null || k == 0)
            return null;
        ListNode p1 = head, p2 = head;
        // 让第一个指针先走到第k个节点
        while(k-- > 0) {
            if(p1 != null)
                p1 = p1.next;
            else
                return null;
        }
        // 然后两个指针再同时走，当第一个指针走到尾的时候，第二个指针指向的就是倒数第k个节点
        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // 删除链表中的倒数第k个节点
    // 删除链表有两种思路：第一种是先找到要删除节点的前一个节点，然后让它的指向要删除节点的下一个节点
    // 另一种思路是先将要删除节点的下一个节点值赋给要删除的节点，然后将要删除节点指向下一个节点的下一个节点，但这种思路有一个问题就是
    // 要删除的节点刚好是尾节点，它没有下一个节点，这时，我们只能从头开始顺序遍历链表，找到待删除节点的前一个节点，完成删除操作
    public ListNode removeKthFromEnd(ListNode head, int k) {
        if(head == null || k == 0)
            return head;
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode kthToTail = findKthToTail(dump, k+1);
        if(kthToTail != null){
            kthToTail.next = kthToTail.next.next;
        }
        return dump.next;
    }

    public static void main(String[] args) {
        KthNodeFromEnd_22 kthNodeFromEnd_22 = new KthNodeFromEnd_22();
        kthNodeFromEnd_22.test1();
        kthNodeFromEnd_22.test2();
    }

    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode kthToTail = findKthToTail(node1, 4);
        System.out.println(kthToTail.val);

        ListNode node = removeKthFromEnd(node1, 4);
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public void test2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);


        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode kthToTail = findKthToTail(node1, 5);
        System.out.println(kthToTail == null ? "null" : kthToTail.val);

        ListNode node = removeKthFromEnd(node1, 1);
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
