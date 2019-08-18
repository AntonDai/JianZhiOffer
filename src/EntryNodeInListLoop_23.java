/**
 * @description: 链表中环的入口节点
 * @author: Daniel
 * @create: 2019-03-04-10-22
 **/
public class EntryNodeInListLoop_23 {
    public ListNode entryNodeOfLoop(ListNode head) {
        if(head == null || head.next == null)
            return null;
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) { // 第一次相遇
                fast = head; // 回到起点
                while(fast != slow) { // 调整速度，直到第二次相遇
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow; // 退出循环，第二次相遇
            }
        }
        return null;
    }
}
