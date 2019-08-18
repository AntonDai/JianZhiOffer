/**
 * @description: 求1+2+...+n
 * @author: Daniel
 * @create: 2019-02-27-15-14
 **/
public class Accumulate_64 {
    public static int sum(int n) {
        int sum = n;
        boolean flag = (n > 0) && (sum += sum(--n)) > 0;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum(100));
    }
}
