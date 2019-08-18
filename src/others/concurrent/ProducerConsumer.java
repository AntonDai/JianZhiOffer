package others.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: 测试生产者消费者
 * @author: Daniel
 * @create: 2019-03-01-22-08
 **/
public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<Product> queue = new ArrayBlockingQueue<>(6); // 使用有界队列
        // 生产者
        new Thread(new Producer(queue),"A").start();
        new Thread(new Producer(queue),"B").start();
        // 消费者
        new Thread(new Consumer(queue),"C").start();
        new Thread(new Consumer(queue),"D").start();

    }
}
