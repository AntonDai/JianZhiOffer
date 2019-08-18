package others.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 栅栏Exchanger
 * @author: Daniel
 * @create: 2019-03-20-17-12
 **/
public class ExchangerDemo {

    /**Exchanger用于进行线程间的数据交换，它提供一个同步点，
     * 在这个同步点，两个线程可以交换彼此的数据。
     * 这两个线程通过exchange 方法交换数据，如果第一个线程先执行exchange 方法，
     * 它会一直等待第二个线程也执行exchange 方法，当两个线程都到达同步点时，这两个线程就可以交换数据。
     */
    private static void doExchangeWork(String data, Exchanger exchanger) {
        try{
            System.out.println(Thread.currentThread().getName() + "正在把数据" + data + "交换出去");
            Thread.sleep((long)(Math.random()*1000));
            String exchangeData = (String) exchanger.exchange(data);
            System.out.println(Thread.currentThread().getName() + "交换得到数据" + exchangeData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Exchanger exchanger = new Exchanger();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                doExchangeWork("data1",exchanger);
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                doExchangeWork("data2",exchanger);
            }
        });
        executor.shutdown();
    }
}
