/**
 * @description: 双端链表查找
 * @author: Daniel
 * @create: 2019-03-18-11-14
 **/
public class DualLinkList {
    private class Node {
        int val;
        Node next;
        Node pre;

        public Node(int val, Node pre, Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // 添加节点
    public void add(int val) {
        final Node last = tail;
        final Node node = new Node(val,last,null);
        tail = node;
        if(last == null) {
            head = node;
        }else {
            last.next = node;
        }
        size++;
    }

    // 删除节点
    public boolean remove(int val) {
        Node node = indexOf(val);
        if(node != null) {
            Node prev = node.pre;
            Node next = node.next;
            if(next == null) {
                tail = prev;
            }else{
                next.pre = prev;
                node.next = null;
            }
            if(prev == null) {
                head = next;
            }else {
                prev.next = next;
                node.pre = null;
            }
            node = null;
            size--;
            return true;
        }
        return false;
    }

    private Node indexOf(int val) {
        for(Node node = head; node != null; node = node.next) {
            if(node.val == val) {
                return node;
            }
        }
        return null;
    }

    // 二分查找，index表示第几个元素
    public int get(int index) {
        if(index < 1 || index > size)
            return -1;
        if(index < (size >> 1)) { // 从头到尾查找
            Node node = head;
            for(int i = 1; i < index; i++)
                node = node.next;
            return node.val;
        }else {
            Node node = tail;
            for(int j = size - 1; j > index; j--)
                node = node.pre;
            return node.val;
        }
    }
    // 打印链表
    public void print() {
        System.out.println("正向遍历为：");
        Node node = head;
        while(node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println();
    }

    public void printDescending() {
        System.out.println("反向遍历为：");
        Node node = tail;
        while(node != null) {
            System.out.print(node.val + " -> ");
            node = node.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DualLinkList dualLinkList = new DualLinkList();
        dualLinkList.add(3);
        dualLinkList.add(2);
        dualLinkList.add(1);
        dualLinkList.add(4);
        dualLinkList.add(7);
        dualLinkList.print();
        dualLinkList.printDescending();
        System.out.println("删除节点2：");
        dualLinkList.remove(2);
        dualLinkList.print();
        dualLinkList.printDescending();
        System.out.println("删除节点7：");
        dualLinkList.remove(7);
        dualLinkList.print();
        dualLinkList.printDescending();
        System.out.println("查找第3个节点：" + dualLinkList.get(3));

    }
}
