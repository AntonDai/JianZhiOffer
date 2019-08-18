/**
 * @description: 删除链表中重复的节点
 * @author: Daniel
 * @create: 2019-02-19-16-13
 **/
public class DeleteDuplicatedNode_18_1 {

    public ListNode deleteDuplication(ListNode head) {
        if(head == null) return head;
        ListNode dumb = new ListNode(-1); // 相当于一个哑结点
        dumb.next = head;
        ListNode cur = head;
        ListNode pre = dumb;
        while(cur != null && cur.next != null) {
            if(cur.val == cur.next.val) {
                int val = cur.val;
                while(cur != null && cur.val == val) // 直到找到不重复的节点或者找不到cur=null
                    cur = cur.next;
                pre.next = cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dumb.next;
    }

    public ListNode deleteDuplicationRecursive(ListNode head) {
        if(head == null || head.next == null) // 0个或1个节点直接返回头结点
            return head;
        if(head.val == head.next.val) { // 当前节点是重复节点
            ListNode cur = head.next.next;
            // 跳过所有与当前节点重复的节点，直到找到第一个不重复的节点
            while(cur != null && cur.val == head.val)
                cur = cur.next;
            return deleteDuplicationRecursive(cur); // 从新的节点开始递归
        }else { // 当前节点不是重复节点
            head.next = deleteDuplicationRecursive(head.next); // 保留当前节点，从下一个节点开始递归
            return head;
        }
    }

    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3_1 = new ListNode(3);
        ListNode node3_2 = new ListNode(3);
        ListNode node4_1 = new ListNode(4);
        ListNode node4_2 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3_1;
        node3_1.next = node3_2;
        node3_2.next = node4_1;
        node4_1.next = node4_2;
        node4_2.next = node5;

        System.out.println("原始的链表为：");
        printList(node1);
//        ListNode newHead = deleteDuplicationRecursive(node1);
        ListNode newHead = deleteDuplication(node1);
        System.out.println("删除重复节点后的链表为：");
        printList(newHead);
    }

    public void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DeleteDuplicatedNode_18_1 deleteDuplicatedNode181 = new DeleteDuplicatedNode_18_1();
        deleteDuplicatedNode181.test1();
    }
}
