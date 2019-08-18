import java.util.HashMap;

/**
 * @description: 测试HashMap在并发环境下会出现死循环的问题
 * @author: Daniel
 * @create: 2019-03-13-17-58
 **/
public class HashMapInfiniteLoop {

    private static HashMap<Integer,String> map = new HashMap<Integer,String>(2, 0.75f); // 设定初始容量为2

    public static void main(String[] args) {

        map.put(5,"C");

        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();
    }
}
