import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: 队列的最大值
 * @author: Daniel
 * @create: 2019-02-25-10-29
 **/
public class QueueWithMax_59_1 {
    private Deque<Integer> max = new LinkedList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public void add(int val) {
        if(max.isEmpty())
            max.offer(val);
        else if(max.peekLast() >= val)
            max.offer(max.peekLast());
        else
            max.offer(val);
        deque.offer(val);

    }

    public int poll() {
        if(deque.isEmpty())
            throw new RuntimeException("queue is null!");
        int val = deque.poll();
        max.poll();
        return val;
    }

    public int max() {
        if(max.isEmpty())
            throw new RuntimeException("queue is null");
        return max.peekLast();
    }

    public static void main(String[] args) {
        QueueWithMax_59_1 queueWithMax591 = new QueueWithMax_59_1();
        queueWithMax591.add(4);
        queueWithMax591.add(5);
        queueWithMax591.add(3);
        queueWithMax591.add(2);
        queueWithMax591.add(5);
        System.out.println(queueWithMax591.poll()); // 4
        System.out.println(queueWithMax591.max()); // 5
        queueWithMax591.poll();
        queueWithMax591.poll();
        System.out.println(queueWithMax591.max()); // 5
    }
}
