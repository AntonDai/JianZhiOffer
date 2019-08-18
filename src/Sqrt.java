/**
 * @description: 实现一个求平方根的函数
 * @author: Daniel
 * @create: 2019-04-06-16-02
 **/
public class Sqrt {
    // 思路一：二分查找
    public static double sqrt(double x, double precise) {
        if(x < 0)
            throw new RuntimeException("Negative number cannot have a sqrt");
        double low = 0;
        double high = x;
        while(high - low > precise) {
            double mid = low + (high - low) / 2;
            double square = mid * mid;
            if(square > x)
                high = mid;
            else if(square < x)
                low = mid;
        }
        return low;
    }

    // 思路二：牛顿迭代法 https://baike.baidu.com/item/%E7%89%9B%E9%A1%BF%E8%BF%AD%E4%BB%A3%E6%B3%95/10887580?fr=aladdin
    public static double sqrt2(double y, double precise) {
        double x0 = y;
        double x1, diff;
        while(true) {
            x1 = x0 - (x0 * x0 - y) / (2 * x0);
            diff = x1 * x1 - y;
            if(diff < precise && diff > -precise)
                return x1;
            x0 = x1;
        }
    }

    public static void main(String[] args) {
        System.out.println(sqrt(5, 1e-6));
        System.out.println(sqrt2(5, 1e-6));
        System.out.println(Math.sqrt(5));
    }
}
