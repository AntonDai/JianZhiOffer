package others.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 使用wait、notify进行线程间通信
 * @author: Daniel
 * @create: 2019-03-21-10-56
 **/
public class ThreadCommunicationByWaitNotify {
    private List<String> list = new ArrayList<>();

    public void testMethod(Object lock) {
        try{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " begin wait");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " end wait");
            }
            System.out.println("testMethod exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {
        try {
            synchronized ((lock)) {
                System.out.println(Thread.currentThread().getName() + " begin notify");
                lock.notify();
                Thread.sleep(1000); // sleep是不释放锁的
                System.out.println(Thread.currentThread().getName() + " end notify");
            }
            Thread.yield();
            System.out.println("synNotifyMethod exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadA(lock).start();
        new ThreadB(lock).start();
    }
}

class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        new ThreadCommunicationByWaitNotify().testMethod(lock);
    }
}

class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        new ThreadCommunicationByWaitNotify().synNotifyMethod(lock);
    }
}

