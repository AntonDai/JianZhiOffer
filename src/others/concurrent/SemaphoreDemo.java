package others.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 现在有三个信号灯，启动10个线程分别获取信号灯，当信号灯被占用时，其他线程只能等待，当信号灯被释放则等待线程获取信号灯。
 * 信号灯可以由一个线程使用，然后由另一个线程来进行释放，而锁只能由同一个线程使用和释放
 * @author: Daniel
 * @create: 2019-03-21-15-56
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3,true); // 默认非公平，可以指定

        for(int i = 0; i < 10; i++) {
            pool.execute(() -> {
                try {
                    semaphore.acquire(); // 获取信号灯许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "进入，当前系统的并发数为：" + (3 - semaphore.availablePermits()));
                try {
                    Thread.sleep((long) (Math.random()*100)); // double 转 long
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "即将离开");
                semaphore.release(); // 释放信号灯
                System.out.println(Thread.currentThread().getName() + "已经离开，当前系统的并发数为：" + (3 - semaphore.availablePermits()));
            });
        }
        pool.shutdown();
    }
}
