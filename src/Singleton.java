/**
 * @description: 双重检查加锁实现单例模式
 * @author: Daniel
 * @create: 2019-02-14 19:59
 **/
public class Singleton {

    private volatile static Singleton instance = null; // 必须是 volatile

    // 私有构造器
    private Singleton() {

    }

    public static Singleton getInstace() {
        // 先检查实例是否存在，如果不存在再进入下面的同步块
        if(instance == null) {
            synchronized (Singleton.class) {
                // 再次检查实例是否存在，如果不存在才真正地创建实例
                if(instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

}
