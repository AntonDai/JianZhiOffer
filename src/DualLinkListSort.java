/**
 * @description: 给一个双端链表排序
 * @author: Daniel
 * @create: 2019-02-17-17-22
 **/
public class DualLinkListSort {

    private class DualLinkList {
        int val;
        DualLinkList pre = null;
        DualLinkList next = null;

        DualLinkList(int val, DualLinkList pre, DualLinkList next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    DualLinkList head;
    DualLinkList tail;
    int size;

    public void add(int val) {
        final DualLinkList last = tail;
        final DualLinkList node = new DualLinkList(val, last, null);
        tail = node;
        if(last == null) // 第一次插入
            head = node;
        else
            last.next = node;
        size++;
    }

    // 冒泡排序
    public void bubbleSort() {
        if(head == null)
            return;
        DualLinkList node = null;
        DualLinkList p1 = null, p2 = null;
        for(DualLinkList cur = head; cur.next != null; cur = cur.next) {
            for(p1 = head,p2 = head.next; p2 != node; p1 = p1.next,p2 = p2.next) {
                if(p1.val >= p2.val) {
                    int temp = p1.val;
                    p1.val = p2.val;
                    p2.val = temp;
                }
            }
            node = p1;
        }
    }

    // 快速排序
    public void quickSort() {
        if(head == null) return;
        quickSort(size,1,size);
    }

    private void quickSort(int length, int low, int high) {
        if(low >= high) return; // 边界条件
        int pivot = partition(length,low,high);
        quickSort(length,low,pivot-1);
        quickSort(length,pivot+1,high);
    }

    private int partition(int length, int low, int high) {
        // 确定切分元素的位置
        int pivot = getNodeFromHead(low).val;
        DualLinkList node1 = null, node2 = null;
        DualLinkList[] nodes = new DualLinkList[2];
        while(low < high) {
            while(low < high) {
                node2 = getNodeFromTail(length - high);
                // 从后往前把小的元素放到低端
                if (node2.val >= pivot) {
                    high--;
                    continue;
                }
                break;
            }
            nodes[0] = getNodeFromHead(low);
            nodes[1] = getNodeFromTail(high);
            swap(nodes);
            // 从前往后把大的元素放到高端
            while(low < high) {
                node1 = getNodeFromHead(low);
                if(node1.val <= pivot) {
                    low++;
                    continue;
                }
                break;
            }
            nodes[0] = getNodeFromTail(high);
            nodes[1] = getNodeFromHead(low);
            swap(nodes);
        }
        return low;
    }

    private void swap(DualLinkList[] nodes) {
        int temp = nodes[0].val;
        nodes[0].val = nodes[1].val;
        nodes[1].val = temp;
    }

    private DualLinkList getNodeFromHead(int low) {
        DualLinkList node = head;
        while(--low > 0) {
            node = node.next;
        }
        return node;
    }

    private DualLinkList getNodeFromTail(int high) {
        DualLinkList node = tail;
        while(--high >= 0)
            node = tail.pre;
        return node;
    }
    // 清空链表
    public void clear() {
        for(DualLinkList node = head; node != null;) {
            DualLinkList next = node.next;
            node.next = null;
            node.pre = null;
            node = next;
        }
        head = tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        DualLinkListSort sort = new DualLinkListSort();
        sort.test1();
        sort.test2();
    }

    public void test(String testId) {
        System.out.println(testId + "测试结果：");
        DualLinkList node = head;
        System.out.print("原始的链表：");
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
//        bubbleSort();
        quickSort();
        node = head;
        System.out.print("排序后的链表：");
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
        System.out.println("-----------------------------");
    }

    public void test1() {
        add(1);
        add(5);
        add(4);
        add(3);
        add(2);

        test("test1");
        clear();
    }

    public void test2() {
        add(1);
        add(4);
        add(4);
        add(3);
        add(3);

        test("test2");
        clear();
    }
}
