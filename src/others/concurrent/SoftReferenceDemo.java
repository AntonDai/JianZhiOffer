package others.concurrent;

import java.lang.ref.SoftReference;

/**
 * @description: 软引用何时被回收
 * 运行参数 -Xmx200m -XX:+PrintGC
 * @author: Daniel
 * @create: 2019-03-20-15-10
 **/
public class SoftReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        // 100M的缓存数据，此时内存充足
        byte[] cacheData = new byte[100 * 1024 * 1024];
        // 将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        // 将缓存数据的强引用去除
        cacheData = null;
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        // 进行一次GC后查看对象的回收情况
        System.gc();
        // 等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        // 再分配一个120M的对象，看看缓存对象的回收情况，此时内存不足
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());

        /**输出结果：运行参数 -Xmx200m -XX:+PrintGC
         * 第一次GC前null
         * 第一次GC前[B@1b6d3586
         * [GC (System.gc())  105064K->103152K(175104K), 0.0011120 secs]
         * [Full GC (System.gc())  103152K->103020K(175104K), 0.0041804 secs]
         * 第一次GC后null
         * 第一次GC后[B@1b6d3586
         * [GC (Allocation Failure)  103685K->103052K(175104K), 0.0009456 secs]
         * [GC (Allocation Failure)  103052K->103052K(175104K), 0.0015189 secs]
         * [Full GC (Allocation Failure)  103052K->103016K(175104K), 0.0050575 secs]
         * [GC (Allocation Failure)  103016K->103016K(199680K), 0.0026928 secs]
         * [Full GC (Allocation Failure)  103016K->598K(124928K), 0.0087142 secs]
         * 分配后null
         * 分配后null
         */
    }
}
