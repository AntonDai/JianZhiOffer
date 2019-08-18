/**
 * @description: 在O(1)时间内删除链表的节点
 * 前提是要删除的节点在链表中，把检查的任务交给函数的调用者，还有个问题就是如何表示一个空表？ ==> 让函数返回头结点
 * @author: Daniel
 * @create: 2019-02-19-14-37
 **/
public class DeleteNodeInList_18 {

    public ListNode deleteNode(ListNode head, ListNode deletedNode) {
        if(head == null || deletedNode == null)
            return head;
        // 要删除的节点存在下一个节点
        if(deletedNode.next != null) {
            ListNode next = deletedNode.next;
            deletedNode.val = next.val;
            deletedNode.next = next.next;
        }else if(deletedNode == head) { // 链表只有一个节点，删除头结点也是尾节点
            deletedNode = null;
            head = null;
        }else { // 链表中有多个节点，删除尾节点
            ListNode cur = head;
            while(cur.next != deletedNode)
                cur = cur.next;
            cur.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        DeleteNodeInList_18 deleteNodeInList18 = new DeleteNodeInList_18();
        deleteNodeInList18.test1();
        deleteNodeInList18.test2();
    }

    public void test(ListNode head, ListNode deletedNode) {
        System.out.println("==============================");
        System.out.println("要删除的节点：" + deletedNode.val);
        System.out.print("原始链表为：");
        printList(head);
        ListNode newHead = deleteNode(head, deletedNode);
        System.out.print("删除节点后的链表为：");
        printList(newHead);
    }

    public void printList(ListNode head) {
        ListNode cur = head;
        while(cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        test(node1,node2); // 删除中间节点
        test(node1,node5); // 删除尾节点
        test(node1,node1); // 删除头结点
    }

    public void test2() {
        ListNode node1 = new ListNode(1);
        test(node1,node1);
    }
}
