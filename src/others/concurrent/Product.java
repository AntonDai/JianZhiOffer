package others.concurrent;

/**
 * @description: 生产者消费者问题之产品类
 * @author: Daniel
 * @create: 2019-03-01-21-55
 **/
public class Product {
    private String id;

    public Product(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "产品-" + id;
    }
}
