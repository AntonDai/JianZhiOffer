import java.util.HashMap;

/**
 * @description: LRU缓存
 * 思路：为什么要用双向链表？
 * 首先，缓存的命中是随机的，与顺序无关；其次，双向链表的插入、删除很快，可以灵活调整相互间的次序，时间复杂度O(1)
 * 为了能够快速删除最久没有访问的数据项和插入最新的数据项，将双向链表连接cache中的数据项，并且保证链表维持数据项从最近访问到最久访问的顺序。
 * 每次数据项被查询时，都将此数据项移动到链表头部（O(1)的时间复杂度）
 * 这样，在进行过多次查找操作后，最近被使用过的内容就向链表的头移动，而没有被使用的内容就向链表的后面移动
 * 当需要替换时，链表最后的位置就是最近最少被使用的数据项，我们只需要将最新的数据项放在链表头部，当Cache满时，淘汰链表最后的位置就是了
 * 为了能减少整个数据结构的时间复杂度，就要减少查找的时间复杂度，遍历链表的时间复杂度是O(n),所以这里利用HashMap来做，这样时间复杂度就是O(1)。
 * @author: Daniel
 * @create: 2019-02-28-16-08
 **/
public class LRUCache {

    private int maxSize; // 最大允许缓存
    Node head, tail;
    HashMap<Integer,Node> map;

    public LRUCache(int capacity) {
        this.maxSize = capacity;
        this.map  = new HashMap<Integer, Node>(capacity);
    }

    // 双向链表的节点类
    private class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return this.key + ": " + this.val;
        }
    }

    // 查询缓存
    public int get(int key) {
        if(map.containsKey(key)) { // 如果cache中存在要找的值，返回其值并将其在原链表中删除，然后将其插入作为头结点
            Node node = map.get(key);
            remove(node);
            setHead(node);
            return node.val;
        }
        return -1; // 如果cache中不存在要get的值，返回-1
    }

    // 更新缓存
    public void set(int key, int val) {
        if(map.containsKey(key)) { // 当要添加的key已经存在，就更新其value值，并将其从链表中删除，然后将其作为头结点
            Node old = map.get(key);
            old.val = val;
            remove(old);
            setHead(old);
        }else {
            // 当要添加的key不存在时，就新建一个节点，如果当前缓存的大小size（可通过map.size()获得）小于最大值，就放入hashmap中，并将其作为头结点；
            // 否则，删除链表最后一个node,再将其放入hashmap，并作为头结点，不更新size
            Node node = new Node(key,val);
            if(map.size() >= maxSize) {
                map.remove(tail.key);
                remove(tail);
                setHead(node);
            } else {
                setHead(node);
            }
            map.put(key,node);
        }
    }

    // 从双向链表中移除节点
    public void remove(Node node) {
        if(node == null) return;
        if(node.pre != null)
            node.pre.next = node.next;
        else // 要删除的节点是头结点
            head = node.next;
        if(node.next != null)
            node.next.pre = node.pre;
        else // 要删除的节点是尾节点
            tail = node.pre;
    }

    // 设置新的头结点
    public void setHead(Node node) {
        node.next = head;
        node.pre = null;

        if(head != null)
            head.pre = node;
        head = node;
        if(tail == null)
            tail = node;
    }

    // 打印缓存
    public void print() {
        Node node = head;
        while(node != null) {
            System.out.print(node.toString() + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.set(1,1);
        cache.set(2,2);
        cache.set(3,3);
        cache.print();
        cache.get(3);
        cache.get(1);
        cache.print();
        cache.set(4,4);
        cache.print();
    }
}
