package others.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 测试Exchanger
 * @author: Daniel
 * @create: 2019-05-04 16:09:41
 **/
public class ExchangerTest {
    private static final Exchanger<String> exchenger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";
                String B = exchenger.exchange(A);
                System.out.println("A 交换后的数据为：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水B";
                String A = exchenger.exchange("B");
                System.out.println("A 和 B 的数据是否一致：" + A.equals(B) + ", A 录入的是：" + A + ", B 录入的是：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
    }
}
