import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description: 从尾到头打印链表
 * @author: Daniel
 * @create: 2019-02-15-16-43
 **/
public class PrintListInReversedOrder {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode four = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = four;

        PrintListInReversedOrder printListInReversedOrder = new PrintListInReversedOrder();
        ArrayList<Integer> list = printListInReversedOrder.printListFromTailToHead(first);
        System.out.println("从尾到头打印链表：" + list);
        list = printListInReversedOrder.printListFromTailToHeadRecursive(first);
        System.out.println("从尾到头打印链表：" + list);
        list = printListInReversedOrder.printListFromTailToHead3(first);
        System.out.println("从尾到头打印链表：" + list);
        // 下面的操作会修改原始链表
        list = printListInReversedOrder.printListFromTailToHead2(first);
        System.out.println("从尾到头打印链表：" + list);

    }

    // 利用栈来实现先进后出，不修改原始的链表结构
    public ArrayList<Integer> printListFromTailToHead(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        if(head == null) return list;
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while(!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    // 递归的本质就是一个栈结构，于是上面的代码也可以用递归来实现
    ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHeadRecursive(ListNode head) {
        if(head != null) {
            printListFromTailToHeadRecursive(head.next);
            list.add(head.val);
        }
        return list;
    }

    // 如果可以修改原始的链表结构的话，则可以先翻转链表，再从头到尾打印
    public ArrayList<Integer> printListFromTailToHead2(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        if(head == null) return list;
        ListNode newHead = null;
        ListNode cur = head;
        while(cur != null) {
            // 下面的三句是翻转链表的关键
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        cur = newHead;
        while(cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list;
    }

    // 还可以直接翻转从头到尾添加的list
    public ArrayList<Integer> printListFromTailToHead3(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> reverseList = new ArrayList<>();
        if(head == null) return reverseList;
        ListNode cur = head;
        while(cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        for(int i=list.size()-1; i>=0; i--)
            reverseList.add(list.get(i));
        return reverseList;
    }
}
