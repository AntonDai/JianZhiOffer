/**
 * @description: 圆圈中最后剩下的数字
 * 约瑟夫环问题
 * @author: Daniel
 * @create: 2019-02-26-10-35
 **/
public class LastNumberInCircle_62 {
    /**先用一个循环链表来模拟环*/
    public static int lastRemaining(int n, int m) { // m为指定要删除的第几个数字
        if(n <= 0 || m <= 0)
            return -1;
        // 构造循环链表（环形链表）
        ListNode head = new ListNode(0);
        ListNode cur = head, next = null;
        for(int i=1; i<n; i++) {
            next = new ListNode(i);
            cur.next = next; // 设置当前节点指向下一个节点
            cur = next; // 再让当前节点指向下一个节点
        }
        cur.next = head; // 构建环，将尾节点指向头结点
        while(n > 1) {
            cur = head;
            // 要删除第m个节点，先要找到第m-1个节点
            for(int i=1; i<m-1; i++) // 这里的i要设为1，可以这么想，假如m=2，那么要找的第m-1个节点就是头节点
                cur = cur.next;
            // 再删除第m个节点
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp = null; // 防止对象游离
            head = cur.next; // 更新头结点
            n--;
        }
        return head.val;
    }

    public static int lastRemainingRecursive(int n, int m) {
        if(n < 1 || m < 1)
            return -1;
        if(n == 1)
            return 0;
        return (lastRemaining(n-1,m) + m) % n;
    }

    public static int lastRemainingIterative(int n, int m) {
        if(n < 1 || m < 1)
            return -1;
        int last = 0; // n = 1 时
        for(int i=2; i<=n; i++) { // n >= 2 时
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5,3));; // 3
        System.out.println((lastRemaining(3,5))); // 0
        System.out.println(lastRemaining(4000,997));

        System.out.println("--------------------------");
        System.out.println(lastRemainingRecursive(5,3));; // 3
        System.out.println((lastRemainingRecursive(3,5))); // 0
        System.out.println(lastRemainingRecursive(4000,997));

        System.out.println("--------------------------");
        System.out.println(lastRemainingIterative(5,3));; // 3
        System.out.println((lastRemainingIterative(3,5))); // 0
        System.out.println(lastRemainingIterative(4000,997));
    }
}
