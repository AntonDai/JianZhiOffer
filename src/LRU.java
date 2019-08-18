import java.util.HashMap;
import java.util.Iterator;

/**
 * @description: 基于双向链表+HashMap的LRU（Least Recently Used）实现
 * ，算法描述：
 * - 访问某个节点时，将其从原来的位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的就是最近最久未使用的节点，当节点数量大于缓存的最大空间时，就淘汰掉链表尾部的节点。
 * - 为了使删除操作的时间复杂度为O(1)，就不能采用遍历的方式找到某个节点。HahsMap存储着key到节点的映射，通过key就能以O(1)的时间得到节点，然后再以O(1)的时间将其从双向链表中删除。
 * @author: Daniel
 * @create: 2019-02-28-15-05
 **/
public class LRU<K, V> implements Iterable<K> {

    private Node head; // 双向链表的头结点
    private Node tail; // 双向链表的尾节点
    private HashMap<K,Node> map;
    private int maxSize; // 缓存的最大空间

    private class Node {
        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize);

        head = new Node(null,null);
        tail = new Node(null,null);

        head.next = tail;
        tail.pre = head;
    }

    // 查询缓存
    public V get(K key) {
        if(!map.containsKey(key))
            return null;
        Node node = map.get(key);
        unlink(node);
        appendHead(node);

        return node.v;
    }

    // 存放缓存
    public void put(K key, V value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }

        Node node = new Node(key,value);
        map.put(key,node);
        appendHead(node);

        if(map.size() > maxSize) { // 超过最大缓存
            Node deleted = removeTail();
            map.remove(deleted.k);
        }
    }

    // 从双向链表中删除节点
    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }

    // 从双向链表的头部插入节点
    private void appendHead(Node node) {
        Node next = head.next;
        node.next = next;
        next.pre = node;
        node.pre = head;
        head.next = node;
    }

    // 从双向链表的尾部移除节点
    private Node removeTail() {
        Node node = tail.pre;

        Node pre = node.pre;
        tail.pre = pre;
        pre.next = tail;

        node.pre = null;
        node.next = null;

        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }
        };
    }

    public static void main(String[] args) {
        LRU<String, Integer> cache = new LRU<>(3);
        cache.put("a",1);
        cache.put("b",2);
        cache.put("c",3);
        System.out.println(cache.get("b"));
        System.out.println(cache.get("a"));
        cache.put("d",4);
        Iterator it = cache.iterator();
        while(it.hasNext()) {
            String value = (String) it.next();
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
