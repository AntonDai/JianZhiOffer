import java.util.HashMap;

/**
 * @description: 最近最少访问算法，按照访问次数，要求get()和set()的时间复杂度都为O(1)
 * @author: Daniel
 * @create: 2019-03-29-09-20
 **/
public class LFU {
    // 链表节点
    public static class Node {
        int key; // 键
        int value; // 值
        int times; // 访问次数
        Node up; // 上一个节点
        Node down; // 下一个节点

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }

        /**
         * 整个数据结构如下：
         *                   _____          _____       _____
         * headList  ->     |____|  <=>   |____| <=>   |____| <=>      times = 1
         *                    ||
         *                   _____          _____       _____
         *                  |____|  <=>   |____| <=>   |____| <=>      times = 2
         *                   ||
         *                  _____         _____       _____
         *                 |____|  <=>   |____| <=>   |____| <=>       times = 3
         *                   ||
         */
        public static class LFUCache {
            // 双端链表
            public static class NodeList {
                Node head; // 链表头节点
                Node tail; // 链表尾节点
                NodeList last; // 链表节点的上一个节点
                NodeList next; // 链表节点的下一个节点

                public NodeList(Node node) {
                    head = node;
                    tail = node;
                }

                public void addNodeFromHead(Node newHead) { // 给链表头部添加新节点
                    newHead.down = head;
                    head.up = newHead;
                    head = newHead;
                }

                public boolean isEmpty() {
                    return head == null;
                }

                public void deleteNode(Node node) {
                    if(head == tail) { // 只有一个节点
                        head = null;
                        tail = null;
                    }else {
                        if(node == head) { // 链表的头节点
                            head = node.down;
                            head.up = null;
                        }else if(node == tail) { // 链表的尾节点
                            tail = node.up;
                            tail.down = null;
                        }else { // 链表的中间节点
                            node.up.down = node.down;
                            node.down.up = node.up;
                        }
                    }
                    node.up = null;
                    node.down = null;
                }
            }

            private int capacity; // 容量
            private int size; // 当前大小
            private HashMap<Integer,Node> records; // 通过key找node
            private HashMap<Node,NodeList> heads; // 找到node当前所在链表
            private NodeList headList; // 双端链表节点的头结点

            public LFUCache(int capacity) {
                this.capacity = capacity;
                this.size = 0;
                this.records = new HashMap<>();
                this.heads = new HashMap<>();
                headList = null; //
            }

            public void put(int key, int value) {
                if(records.containsKey(key)) { // 如果key已经存在
                    Node node = records.get(key); // 获取key所在节点
                    node.value = value; // 更新值
                    node.times++; // 访问次数加一
                    NodeList curNodeList = heads.get(node); // 获取节点所在链表
                    move(node,curNodeList); // 从旧链表中移除当前节点，放入新链表中
                }else {
                    if(size == capacity) {
                        Node node = headList.tail;
                        headList.deleteNode(node); // 删除尾节点
                        modifyHeadList(headList); // 检查是否需要调整，有可能删光了
                        records.remove(node.key);
                        heads.remove(node);
                        size--;
                    }
                    Node node = new Node(key,value,1);
                    if(headList == null) // 第一次加
                        headList = new NodeList(node);
                    else { // 检查是否存在专属的次数链表
                        if(headList.head.times == node.times)
                            headList.addNodeFromHead(node); // 每次添加节点都往链表的头部插入
                        else { // 没有就建
                            NodeList newList = new NodeList(node);
                            newList.next = headList;
                            headList.last = newList;
                            headList = newList;
                        }
                    }
                    // 记录信息
                    records.put(key,node);
                    heads.put(node,headList);
                    size++;
                }
            }
            // 访问节点后，需要将节点从旧的链表（里面节点的访问次数为i）中删除，并移动到新的链表（里面的节点访问次数为i+k(k>=1)）中
            public void move(Node node, NodeList oldNodeList) {
                oldNodeList.deleteNode(node); // 从旧链表中删除节点，删除后，旧链表可能为null，也可能不为null，需要调整
                NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList; // 获得该链表所在的前一个节点，如果删除节点后的当前链表不为空，那就为当前节点，否则，就取当前链表节点的上一个节点
                NodeList nextList = oldNodeList.next; // 获取该链表所在的下一个节点
                if(nextList == null) {
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList; // 将新创建的链表放到原来链表的后面
                    }
                    newList.last = preList;
                    if(headList == null) {
                        headList = newList; // 让头节点指向新链表
                    }
                    heads.put(node,newList); // 存储node所在链表的记录
                }else {
                    if(nextList.head.times == node.times) { // 若下一条链表的节点访问次数刚好等于次节点的访问次数，则放入下一条链表
                        nextList.addNodeFromHead(node);
                        heads.put(node,nextList);
                    }else { // 否则的话，新建一条链表，并且插入到两条链表的中间
                        NodeList newList = new NodeList(node);
                        if(preList != null)
                            preList.next = nextList;
                        newList.last = preList;
                        newList.next = nextList;
                        nextList.last = newList;
                        if(headList == nextList) // 判断列表头是否有变化，在modifyHeadList中可能会修改头结点
                            headList = newList;
                        heads.put(node,newList);
                    }
                }
            }

            private boolean modifyHeadList(NodeList nodeList) {
                if(nodeList.isEmpty()) { // 如果删除节点后的链表变为空，就要删除该链表
                    if(headList == nodeList) { // 如果是头结点
                        headList = nodeList.next; // 让头结点指向下一个链表
                        if (headList != null) // 尾部的情况
                            headList.last = null;
                    }else { // 普通结点
                        nodeList.last.next = nodeList.next;
                        if (nodeList.next != null) // 尾部的话就是null
                            nodeList.next.last = nodeList.last;
                    }
                    return true;
                }
                return false;
            }

            public int get(int key) {
                if(!records.containsKey(key))
                    return -1;
                Node node = records.get(key);
                node.times++; // 节点访问次数加一
                NodeList curNodeList  = heads.get(node); // 获取节点所在链表
                move(node,curNodeList); // 将节点移动到新的链表中
                return node.value;
            }
        }

        public static void main(String[] args) {
            LFUCache cache = new LFUCache(2);
            cache.put(1,1);
            cache.put(2,2);
            System.out.println(cache.get(1));
            cache.put(3,3); // 移除键2
            System.out.println(cache.get(2));
            System.out.println(cache.get(3));
            cache.put(4,4); // 移除键1
            System.out.println(cache.get(1));
            System.out.println(cache.get(3));
            System.out.println(cache.get(4));
        }
    }
}
