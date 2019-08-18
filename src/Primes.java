import java.util.ArrayList;

/**
 * @description: 打印1000之内的所有素数
 * @author: Daniel
 * @create: 2019-03-01-22-46
 **/
public class Primes {

    /**思路： 2*2 2*3 2*4 ... 2*m
     *        3*2 3*3 3*4 3*5 ... 3*m
     *        4*2 4*3 4*4 ... 4*m ... 肯定都不是素数*/
    public static ArrayList<Integer> getPrimes(int n) {
        boolean[] notPrime = new boolean[n+1];
        ArrayList<Integer> list = new ArrayList<>();
        // 质数（又称素数）：在大于 1 的自然数中，除了 1 和它本身以外不再有其他因数。
        for(int i=2; i<=n; i++) {
            if(!notPrime[i]) {
                list.add(i);
                for(int j=2; i*j<=n; j++)
                    notPrime[i*j] = true;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> primes = getPrimes(1000);
        System.out.println(primes);
        System.out.println(primes.size());
    }
}
