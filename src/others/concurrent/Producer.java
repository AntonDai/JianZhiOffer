package others.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * @description: 生产者消费者之生产者
 * @author: Daniel
 * @create: 2019-03-01-21-57
 **/
public class Producer implements Runnable{
    private BlockingQueue<Product> products;

    public Producer(BlockingQueue<Product> products) {
        this.products = products;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            Product product = new Product(Thread.currentThread().getName() + i);
            try{
                products.put(product); // 当队列已满时，调用put()会阻塞
                System.out.println("生产者" + Thread.currentThread().getName() + "生产了" + product);
                Thread.sleep((long) (Math.random()*1000));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
