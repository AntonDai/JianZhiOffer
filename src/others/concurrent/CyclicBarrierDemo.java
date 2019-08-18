package others.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 使用栅栏先召集7个法师，等他们都找到龙珠后，再一起来找我召唤神龙
 * @author: Daniel
 * @create: 2019-03-20-16-41
 **/
public class CyclicBarrierDemo {
    private static final int THREAD_COUNT_NUM = 7;

    public static void main(String[] args) {
        // 设置第一个屏障，等待召集齐7位法师
        CyclicBarrier callMasterBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("7个法师召集完毕，同时出发，去往不同的地方寻找龙珠！");
                summonDragon();
            }
        });
        // 召集齐7位法师
        for(int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try{
                    System.out.println("召集第" + index + "个法师");
                    callMasterBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    // 召唤神龙
    private static void summonDragon() {
        // 设置第二个屏障点，等待7位法师收集完7颗龙珠，召唤神龙
        CyclicBarrier summonDragonBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("集齐七颗龙珠！召唤神龙！");
            }
        });
        // 收集7颗龙珠
        for(int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try{
                    System.out.println("第" + index + "颗龙珠已经收集到！");
                    summonDragonBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
