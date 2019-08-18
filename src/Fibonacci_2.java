/**
 * @description: 变态跳台阶问题
 * @author: Daniel
 * @create: 2019-02-16-17-09
 **/
public class Fibonacci_2 {
    public int jumpFloorII(int n) {
        if(n <= 0) return 0;
        int first = 1;
        for(int i=2; i<=n; i++) {
            first <<= 1; // 相当于 first *= 2;
        }
        return first;
    }
}
