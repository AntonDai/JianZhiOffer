package others.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 使用Lock和Condition交替打印ABC
 * @author: Daniel
 * @create: 2019-03-11-19-30
 **/
public class AlternativePrintABC {
    public static void main(String[] args) {
        int count = 3; // 打印线程数
        int times = 10; // 循环打印次数
        PrintABC printABC = new PrintABC(count,times);
        for(int i=0; i<count; i++)
            new Thread(printABC,(char)(i + 'A') + "").start(); // 线程名分别为A B C
    }
}

class PrintABC implements Runnable {
    private char nextThread = 'A';
    private Lock lock = new ReentrantLock();
    private Condition[] conditions;
    private int times; // 循环次数

    public PrintABC(int count, int times) {
        this.times = times;
        conditions = new Condition[count]; // 打印线程数量
        for(int i=0; i<count; i++)
            conditions[i] = lock.newCondition();
    }

    @Override
    public void run() {
        for(int i=0; i<times; i++) {
            lock.lock();
            char curThread = Thread.currentThread().getName().charAt(0); // 获取当前线程名
            try{
                if(curThread != nextThread)
                    conditions[curThread-'A'].await();
                System.out.print(curThread);
                // A->A+1=B->A+2=C->A+0=A
                nextThread = (char) ('A' + (nextThread + 1 - 'A') % conditions.length); // conditions.length 等于打印线程数量
                conditions[nextThread-'A'].signal();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
