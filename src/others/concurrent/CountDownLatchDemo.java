package others.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 创建一定数量的线程，利用他们并行地执行指定的任务
 * @author: Daniel
 * @create: 2019-03-20-16-11
 **/
public class CountDownLatchDemo {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        /**使用两个闭锁，分别表示起始门和结束门。
         * 起始门计数器初始值为1，结束门计数器初始值为工作线程的数量。
         * 每个工作线程首先要做的事就是在启动门上等待，从而确保所有的线程都就绪后才开始执行。
         * 而每个线程要做的最后一件事是将调用结束门的countDown方法减一，这能使主线程高效地等待直到所有的工作线程都执行完成，
         * 因此可以统计所消耗的时间。
         */
        final CountDownLatch startGate = new CountDownLatch(1); // 使主线程能够同时释放所有工作线程
        final CountDownLatch endGate = new CountDownLatch(nThreads); // 使主线程能够等待最后一个线程执行完成

        for(int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    }finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Thread task = new Thread(() -> {
            int index = atomicInteger.getAndIncrement();
            System.out.println("第" + index + "颗龙珠已收集到！");
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        long time = countDownLatchDemo.timeTasks(7, task);
        System.out.println("用时" + (time/1e9) + "s集齐七颗龙珠！召唤神龙！");
    }
}
