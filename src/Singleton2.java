/**
 * @description: 静态内部类实现单例模式
 * @author: Daniel
 * @create: 2019-02-14 20:14
 **/
public class Singleton2 {

    {
        System.out.println("正在初始化Singleton");
    }
    // 私有构造器
    private Singleton2() {

    }
    // 只有在被调用的时候才会装载
    private static class SingletonHolder {
        static {
            System.out.println("正在初始化SingletonHolder");
        }
        private static Singleton2 instance = new Singleton2();

    }

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        Singleton2.getInstance();
    }
}
