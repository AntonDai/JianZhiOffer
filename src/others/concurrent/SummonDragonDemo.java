package others.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 使用CountDownLatch集齐七颗龙珠召唤神龙
 * @author: Daniel
 * @create: 2019-03-20-15-57
 **/
public class SummonDragonDemo {
    private static final int THREAD_COUNT_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);

    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try{
                    System.out.println("第" + index + "颗龙珠已收集到！");
                    // 模拟收集第i颗龙珠，随机模拟不同的寻找时间
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 每收集到一颗龙珠，需要等待的颗数减一
                countDownLatch.countDown();
            }).start();
        }
        // 等待检查，即上述7个线程执行完，执行await后面的代码
        countDownLatch.await();
        System.out.println("集齐七颗龙珠！召唤神龙！神龙现身！！！");
    }
}
