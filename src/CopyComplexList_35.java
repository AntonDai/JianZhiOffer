/**
 * @description: 复杂链表的复制
 * @author: Daniel
 * @create: 2019-02-20-16-27
 **/
public class CopyComplexList_35 {

    public RandomListNode clone(RandomListNode head) {
        if(head == null) return null;
        RandomListNode cur = head;
        // 1. 遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面
        while(cur != null) {
            RandomListNode cloneNode = new RandomListNode(cur.val);
            RandomListNode next = cur.next;
            cur.next = cloneNode;
            cloneNode.next = next;
            cur = next;
        }
        // 2. 重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        cur = head;
        while(cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分链表，将链表拆分成原链表和复制后的链表
        cur = head;
        RandomListNode cloneHead = head.next;
        while(cur != null) {
            RandomListNode cloneNode = cur.next;
            cur.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            cur = cur.next;
        }
        return cloneHead;
    }

    public static void main(String[] args) {
        CopyComplexList_35 copyComplexList35 = new CopyComplexList_35();
        copyComplexList35.test();
    }

    public void test() {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);

        node1.next = node2;
        node1.random = node3;
        node2.next = node3;
        node2.random = node5;
        node3.next = node4;
        node4.next = node5;
        node4.random = node2;

        printList(node1);
        RandomListNode cloneNode = clone(node1);
        printList(cloneNode);
    }

    public void printList(RandomListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
