/**
 * @description: 斐波那契数列的应用——青蛙跳台阶问题
 * 思路：f(1) = 1 , f(2) = 2, f(n) = f(n-1) + f(n-2) n >= 3
 * @author: Daniel
 * @create: 2019-02-16-17-02
 **/
public class Fibonacci_1 {
    public int jumpFloor(int n) {
        if(n <= 0) return 0;
        int first = 1;
        int second = 2;
        for(int i=3; i<=n; i++) {
            int temp = second;
            second += first;
            first = temp;
        }
        return n == 1 ? first : second;
    }
}
