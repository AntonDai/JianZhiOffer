/**
 * @description: 合并两个排序的链表
 * @author: Daniel
 * @create: 2019-03-03-08-41
 **/
public class MergeSortedLists_25 {

    public ListNode merge(ListNode list1, ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        if(list1.val > list2.val) {
            list2.next = merge(list1, list2.next);
            return list2;
        }
        else {
            list1.next = merge(list1.next, list2);
            return list1;
        }
    }

    public ListNode merge2(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode cur1 = head == list1 ? list1 : list2;
        ListNode cur2 = head == list1 ? list2 : list1;
        ListNode pre = cur1; // 用来记录cur1的前一个节点
        ListNode next = null; // 用来记录cur2的下一个
        while (cur1 != null && cur2 != null) {
            if (cur1.val > cur2.val) {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            } else {
                pre = cur1;
                cur1 = cur1.next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;

        return head;
    }

    public static void main(String[] args) {
        MergeSortedLists_25 mergeSortedLists_25 = new MergeSortedLists_25();
        mergeSortedLists_25.test1();
    }

    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        ListNode node9 = new ListNode(9);

        // 3-4-6-7
        node3.next = node4;
        node4.next = node6;
        node6.next = node7;
        // 1-2-5-8-9
        node1.next = node2;
        node2.next = node5;
        node5.next = node8;
        node8.next = node9;

        ListNode head = merge2(node3, node1);

        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
