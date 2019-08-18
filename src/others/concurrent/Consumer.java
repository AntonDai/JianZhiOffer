package others.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * @description: 生产者与消费者之消费者
 * @author: Daniel
 * @create: 2019-03-01-22-03
 **/
public class Consumer implements Runnable{
    private BlockingQueue<Product> products;

    public Consumer(BlockingQueue<Product> products) {
        this.products = products;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            try{
                Product product = products.take(); // 当队列为空时，调用take()会阻塞
                System.out.println("消费者" + Thread.currentThread().getName() + "消费了" + product);
                Thread.sleep((long) (Math.random() * 1000));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
