package others.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @description: 三个线程分别打印A，B，C，要求这三个线程一起运行，打印n次，输出形如“ABCABCABC….”的字符串。
 * 思路：三个线程对应三个Semaphore，三个Semaphore维护一个Permit。当前线程通过对应的Semaphore获取Permit，执行打印，并通过下一个线程对应的Semaphore释放Permit。
 * 类似于Permit在当前的线程对应的Semaphore中，传递到了下一个线程对应的Semaphore中。下一个线程通过对应的Semaphore获取Permit，继续执行……循环10次。
 *
 * 效率：每个线程使用一个Semaphore，一个Permit在不同的Semaphore之间循环传递，当前线程消费完Permit后，无法立即进行下一次打印，
 * 而下一个线程使用的Semaphore刚好获取到了Permit，从而使线程可以交替执行。代码简洁，效率高。
 * @author: Daniel
 * @create: 2019-03-11-17-52
 **/
public class PrintABCUsingSemaphore {
    private int times;
    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    public PrintABCUsingSemaphore(int times) {
        this.times = times;
    }

    public void print(String name, Semaphore current, Semaphore next) throws InterruptedException {
        for(int i=0; i<times; i++) {
            // 获取许可
            current.acquire(); // 会抛出InterruptedException
            System.out.print(name);
            // 释放许可
            next.release();
        }
    }

    public void printA() {
        try{
            print("A",semaphoreA,semaphoreB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printB() {
        try {
            print("B",semaphoreB,semaphoreC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printC() {
        try {
            print("C",semaphoreC,semaphoreA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingSemaphore printABC = new PrintABCUsingSemaphore(10);
        // 非静态方法引用，f::toString 等价于 () -> f.toString()
        new Thread(printABC::printA).start();
        new Thread(printABC::printB).start();
        new Thread(printABC::printC).start();
    }
}
