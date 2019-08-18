package others.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @description: 测试弱引用
 * @author: Daniel
 * @create: 2019-03-20-12-27
 **/
public class TestWeakReference {
    static class People {
        private String username;
        private int age;

        public People(String username, int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("People is dead!");
        }

        @Override
        public String toString() {
            return "People{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class Daniel extends WeakReference<People> {
        public Daniel(People people) {
            super(people);
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        Daniel daniel = new Daniel(new People("daniel",24));
        // 通过WeakReference的get()方法获取People
        System.out.println(daniel.get());
        System.gc();
        try {
            // 休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果为空，代表被回收了
        if (daniel.get() == null) {
            System.out.println("Daniel is over");
        }
    }

    public static void test2() {
        ReferenceQueue<People>  referenceQueue = new ReferenceQueue<>();
        WeakReference<People> Mike = new WeakReference<People>(new People("Mike",24), referenceQueue);
        WeakReference<People> Linda = new WeakReference<People>(new People("Linda",23), referenceQueue);

        System.out.println("=====gc调用前=====");
        Reference<? extends People> ref = null;
        while ((ref = referenceQueue.poll()) != null ) {
            // 不会输出，因为没有回收被弱引用的对象，并不会加入队列中
            System.out.println(ref);
        }
        System.out.println(Mike);
        System.out.println(Linda);
        System.out.println(Mike.get());
        System.out.println(Linda.get());

        System.out.println("=====调用gc=====");
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=====gc调用后=====");

        // 下面两个输出为null,表示对象被回收了
        System.out.println(Mike.get());
        System.out.println(Linda.get());

        // 输出结果，并且就是上面的Mike、Linda，再次证明对象被回收了
        Reference<? extends People> ref2 = null;
        while ((ref2 = referenceQueue.poll()) != null ) {
            // 如果使用继承的方式就可以包含其他信息了
            System.out.println("referenceQueue中：" + ref2);
        }
    }
}
