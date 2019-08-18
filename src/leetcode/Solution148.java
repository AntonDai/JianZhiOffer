package leetcode;

/**
 * @description: 排序列表
 * @author: Daniel
 * @create: 2019-04-26 22:23:07
 **/
public class Solution148 {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode right = mid.next;
        mid.next = null; // 将原来的链表拆开
        ListNode list1 = sortList(head);
        ListNode list2 = sortList(right);
        return merge(list1, list2);
    }

    // 归并两个有序链表
    private static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode head = null, cur = null;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                if (head == null) { // 初始化
                    head = list1;
                    cur = list1;
                } else {
                    cur.next = list1;
                    cur = cur.next;
                }
                list1 = list1.next;
            } else {
                if (head == null) { // 初始化
                    head = list2;
                    cur = list2;
                } else {
                    cur.next = list2;
                    cur = cur.next;
                }
                list2 = list2.next;
            }
        }
        cur.next = list1 != null ? list1 : list2;
        return head;
    }

    // 获取中间节点，偶数个则取中间的第一个节点，奇数个取中间节点
    private static ListNode getMid(ListNode head) {
        if (head == null)
            return null;
        // 设置两个快慢指针，当快指针走到末尾的时候，慢指针刚好在中间节点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node4.next = node2;
        node2.next  = node1;
        node1.next = node3;

        sortList(node4);
    }
}
