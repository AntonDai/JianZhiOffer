/**
 * @description: 两个链表的第一个公共节点
 * @author: Daniel
 * @create: 2019-02-23-16-14
 **/
public class FirstCommonNodesInLists_52 {
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        int len1 = getListLength(pHead1);
        int len2 = getListLength(pHead2);
        ListNode pLong = pHead1, pShort = pHead2;
        int dif = len1 - len2;
        if(len1 < len2) {
            pLong = pHead2;
            pShort = pHead1;
            dif = len2 - len1;
        }
        // 先让长链表走几步，再两个链表同时走
        for(int i=0; i<dif; i++)
            pLong = pLong.next;
        // 再同时走,两个链表肯定有公共的尾部都为null
        while(pLong != null && pShort != null && pLong != pShort) {
            pLong = pLong.next;
            pShort = pShort.next;
        }
        return pLong;
    }

    public ListNode findFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1 != p2) {
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return p1;
    }

    public int getListLength(ListNode pHead) {
        int length = 0;
        while(pHead != null) {
            length++;
            pHead = pHead.next;
        }
        return length;
    }

    public static void main(String[] args) {
        FirstCommonNodesInLists_52 firstCommonNodesInLists52 = new FirstCommonNodesInLists_52();
        firstCommonNodesInLists52.test1();
        firstCommonNodesInLists52.test2();
        firstCommonNodesInLists52.test3();
    }

    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node6.next = node7;

        node4.next = node5;
        node5.next = node6;

        ListNode firstCommonNode = findFirstCommonNode(node1, node4);
        ListNode firstCommonNode2 = findFirstCommonNode2(node1, node4);

        System.out.println(firstCommonNode == null ? "null" : firstCommonNode.val);
        System.out.println(firstCommonNode2 == null ? "null" : firstCommonNode2.val);
    }

    public void test2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node5.next = node6;
        node6.next = node7;

        ListNode firstCommonNode = findFirstCommonNode(node1, node5);
        ListNode firstCommonNode2 = findFirstCommonNode2(node1, node5);

        System.out.println(firstCommonNode == null ? "null" : firstCommonNode.val);
        System.out.println(firstCommonNode2 == null ? "null" : firstCommonNode2.val);
    }

    public void test3() {
        ListNode firstCommonNode = findFirstCommonNode(null, null);
        ListNode firstCommonNode2 = findFirstCommonNode2(null,null);

        System.out.println(firstCommonNode == null ? "null" : firstCommonNode.val);
        System.out.println(firstCommonNode2 == null ? "null" : firstCommonNode2.val);
    }
}
