package others.concurrent;

/**
 * @description: 死锁
 * @author: Daniel
 * @create: 2019-03-01-22-20
 **/
public class DeadLock implements Runnable{
    private int flag = 0;
    Object o1, o2;

    public DeadLock(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("Thread: " + flag);
        if(flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(1);
                }
            }
        }
        if(flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(0);
                }
            }
        }


    }

    public static void main(String[] args) {
        // 构成死锁
        Object o1 = new Object();
        Object o2 = new Object();
        DeadLock thread1 = new DeadLock(o1,o2);
        DeadLock thread2 = new DeadLock(o1,o2);

        // 不构成死锁
//        others.concurrent.DeadLock thread1 = new others.concurrent.DeadLock(new Object(),new Object());
//        others.concurrent.DeadLock thread2 = new others.concurrent.DeadLock(new Object(),new Object());

        thread1.setFlag(1);
        thread2.setFlag(0);

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
