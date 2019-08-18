/**
 * @description: 求第k小的双素数
 * 判断一个数是否是素数 https://blog.csdn.net/afei__/article/details/80638460
 * @author: Daniel
 * @create: 2019-04-02-15-13
 **/
public class DualPrimes {
    public static int findKthDualPrime(int k) {
        int i = 2, j = 0, result = 0;
        while(true) {
            // 一个数是双素数当且仅当它本身是一个素数，十进制反转后不等于它本身且仍然是一个素数
            if(isPrime(i++) && (j = reverse(i)) != i && isPrime(j)) {
                k--;
            }
            if(k == 0) {
                result = i - 1;
                break;
            }
        }
        return result <= 10e6 ? result : -1;
    }
    // 判断一个数是否是素数
    public static boolean isPrime(int n) {
        if(n <= 3)
            return n > 1;
        // 大于等于5的素数中，不在6的倍数的两侧的一定不是素数
        if(n % 6 != 1 && n % 6 != 5) // 大于等于5的质数一定和6的倍数相邻，即 6x-1，6x+1，x>=1
            return false;
        int m = (int) Math.sqrt(n);
        // 能走到for循环这,那么这个数肯定是在6的两侧，只剩下这么两种可能:
        // 1. 可以被分解成(6x-1)(6y-1),(6x-1)(6y+1),(6x+1)(6y-1),(6x+1)(6y+1)这四种式子,
        // 2. 不能被分解(那就是质数)
        for(int i = 5; i <= m; i += 6) { // 步长设为6，每次只判断6两侧的数
            if(n % i == 0 || n % (i + 2) == 0) // 能够被6x-1或6x+1整除就不是素数
                return false;
        }
        return true;
    }

    // 倒序一个整数，如123变321
    public static int reverse(int n) {
        int m = 0;
        while(n != 0) {
            int t = n % 10;
            m = m * 10 + t;
            n = n / 10;
        }
        return m;
    }

    public static void main(String[] args){
        System.out.println(findKthDualPrime(1));
    }
}
