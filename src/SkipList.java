import java.util.ArrayList;
import java.util.List;

/**
 * @description: 跳跃表java实现
 * @author: Daniel
 * @create: 2019-03-30-09-42
 **/
public class SkipList<T extends Comparable<? super T>> {
    // 跳跃表节点
    public static class SkipListNode<T> {
        T key; // 存储的数据
        double score; // 跳跃表按照这个分数值进行从小到大排序
        SkipListNode<T> next, down; // 指向同层下一个节点的next指针，指向下一层的down指针

        public SkipListNode() {}

        public SkipListNode(T key, double score) {
            this.key = key;
            this.score = score;
        }
    }

    private static final int MAX_LEVEL = 1 << 5; // 32
    private static final double PROBABILITY = 0.5;

    private SkipListNode<T> top; // 头节点，用来遍历整个跳跃表
    private int level = 1; // 初始化为L1层

    public SkipList() {
        this.top = new SkipListNode<>(null,Double.MIN_VALUE);
    }

    // 抛硬币，产生节点的高度
    private int getRandomLevel() {
        int level = 1;
        while(Math.random() < PROBABILITY)
            level++;
        return level > MAX_LEVEL ? MAX_LEVEL : level;
    }

    // 查找跳跃表的一个值
    public T search(double score) {
        SkipListNode<T> t = top;
        while(t != null) {
            if(t.score == score)
                return t.key; // 找到了，返回key
            if(t.next == null) { // 当前层没有，往下层找
                if(t.down != null) {
                    t = t.down; // 向下找
                    continue; // 跳出当前循环，遍历下层链表
                }else {
                    return null; // 已经到了最底层
                }
            }
            // 遍历当前层链表
            if(t.next.score > score)
                t = t.down; // 当前层下一个节点比查找值大，往下层找
            else
                t = t.next; // 继续向右找
        }
        return null; // 找不到，返回null
    }

    public void insert(T key, double score) {
        // 找到需要插入的位置
        SkipListNode<T> t = top, cur = null; // 若cur不为空，表示当前score值的节点存在
        List<SkipListNode<T>> path = new ArrayList<>(); // 记录每一层当前节点的前驱节点，注意每次新插入一个节点都会对应一个新的path
        while(t != null) {
            if(t.score == score) {
                cur = t;
                break; // 表示存在该值的节点，需要更新此节点，直接跳出循环
            }
            if(t.next == null) { // 当前层走到头了
                path.add(t); // 需要向下找，先记录该节点
                if(t.down != null) {
                    t = t.down;
                    continue; // 开始遍历下层链表
                }else {
                    break; // 已经到了最底层了
                }
            }
            // 运行到这，是在遍历当前层所在链表
            if(t.next.score > score) {
                path.add(t); // 下一个节点值大于代插入的值，需要向下查找，先记录该节点
                if(t.down == null)
                    break; // 已经到了最底层
                t = t.down;
            }else { // 当前层链表向右遍历
                t = t.next;
            }
        } // 出了这个循环，表明已经找到了待插入节点要插入的位置
        path.forEach(e -> System.out.print(e.key + " ")); // 打印path看一下，插入多少个节点就会打印多少行
        System.out.println();
        if(cur != null) { // 只有已存在该节点时，cur才不为空，更新每一层的cur
            while(cur != null) {
                cur.key = key;
                cur = cur.down; // 更新下层，一直到最底层
            }
        }else { // 当前表中不存在score值的节点，需要从下往上插入
            int lev = getRandomLevel(); // 先获取新插入节点要跨越的层数
            if(lev > level) { // 需要更新top这一列的节点数量，同时需要在path中增加这些新的首节点
                SkipListNode<T> temp = null;
                SkipListNode<T> prev = top; // 前驱节点现在是top了
                while(level++ < lev) {
                    temp = new SkipListNode<>(null,Double.MIN_VALUE);
                    path.add(0,temp); // 加到path的首部
                    temp.down = prev;
                    prev = temp; // 注意，这里更新的顺序是从下往上
                }
                top = temp; // 更新头节点为最高层
                level = lev; // level长度增加到新的长度
            }
            path.forEach(e -> System.out.print("---------" + e.key + " ")); // 打印path看一下，这里是修正后的path，前面有null，表示新增了一层
            System.out.println();
            // 从后向前遍历path中的每一个节点，在其后面增加一个新的节点
            SkipListNode<T> downTemp = null, temp = null, prev = null;
            for(int i = level - 1; i >= 0; i--) {
                temp = new SkipListNode<>(key, score); // 新插入的节点
                prev = path.get(i); // 获取每一层新插入节点的前驱节点
                // 下面两句先更新当前层next指针
                temp.next = prev.next;
                prev.next = temp;
                // 这里再更新当前层down指针
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    // 根据score的值删除节点
    public void delete(double score) {
        // 查找到节点列的第一个节点的前驱节点t
        SkipListNode<T> t = top;
        while(t != null) {
            if(t.next == null) { // 当前层找完了没找到，往下层找
                t = t.down;
                continue;
            }
            if(t.next.score == score) {
                // 在这里说明找到了该删除的节点
                t.next = t.next.next;
                t = t.down;
                // 删除当前节点后，还需要继续删除下层的相同节点
                continue;
            }
            if(t.next.score > score)
                t = t.down; // 往下找
            else
                t = t.next; // 往右找
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipListNode<T> t = top, next = null;
        while(t != null) {
            next = t;
            while(next != null) {
                sb.append(next.score).append(" ");
                next = next.next;
            }
            sb.append("\n");
            t = t.down;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        for(int i = 1; i < 10; i++)
            skipList.insert(i,i);
//        skipList.delete(6);
//        System.out.println(skipList.search(4));
        System.out.println(skipList);
    }
}
