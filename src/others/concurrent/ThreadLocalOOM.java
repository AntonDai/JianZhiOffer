package others.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: ThreadLocal导致的内存溢出
 * @author: Daniel
 * @create: 2019-03-20-08-36
 **/
public class ThreadLocalOOM {

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for(int i=0; i<500; i++) {
            executorService.execute(() -> {
                threadLocal.set(new ThreadLocalOOM().addBigObject());
                System.out.println(Thread.currentThread().getName());
                threadLocal.remove(); // 使用完调用remove方法，清除数据
            });
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<User> addBigObject() {
        List<User> list = new ArrayList<>(10000);
        for(int i=0; i<10000; i++) {
            list.add(new User("daniel","******", "man", 24));
        }
        return list;
    }

    class User {
        private String username;
        private String password;
        private String sex;
        private int age;

        public User(String username, String password, String sex, int age) {
            this.username = username;
            this.password = password;
            this.sex = sex;
            this.age = age;
        }
    }
}
