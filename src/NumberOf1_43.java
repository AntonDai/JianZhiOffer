/**
 * @description: 1~n整数中1出现的次数
 * @author: Daniel
 * @create: 2019-03-09-20-57
 **/
public class NumberOf1_43 {
    /**以 n = 3141592为例
     * 个位2，当个位为1时，前面的六位可由 0~314159组成，有314160种情况
     * 十位9，当十位取值1时，前面的五位数字可由0 ~ 31415组成（受高位影响），十位之后的一位可由0 ~ 9组成（受低位影响），组合情况31416*10=314160种情况
     * 百位5，当百位取值为1时，前面的四位数字可由0~3141组成，百位之后的两位可由0~99组成，组合情况为3142*100=314200种情况
     * 千位1，千位取值即1，前面的三位数字可由0 ~ 314组成，但是当前面的值为314时，后面的三位只有0 ~ 592种情况(特殊情况)，
     * 其余的情况即为前面的值为0 ~ 313,后面三位有0 ~ 999,情况数为314000，所以总情况数为314000 + 593=314593种情况
     * i	   a	b	     count
     * 1	3141592	0	(3141592+8)/10*1+0=314160
     * 10	314159	2	(314159+8)/10*10+0=314160
     * 100	31415	92	(31415+8)/10*100+0=314200
     * 1000	3141	592	(3141+8)/10*1000+1*(592+1)=314593
     * */
    public static int numberOf1Between1AndN(int n) {
        if(n < 1) return 0;
        int count = 0;
        for(long i=1; i<=n; i*=10) {
            long a = n / i; // 高位
            long b = n % i; // 低位
            count += (a + 8) / 10 * i + (a % 10 == 1 ? 1 : 0) * (b + 1); // 当a的最后一位是0或1时，加8不产生进位 其中(a+8)后 /10 是取a的高位，*i表示低位的取值数目与高位的组合
        }
        return count;
    }

    public static int numberOf1Between1AndN_2(int n) {
        int count = 0;
        for(int i=1; i<=n; i++) {
            count += numberOf1(i);
        }
        return count;
    }

    public static int numberOf1(int n) {
        int count = 0;
        while(n != 0) {
            if(n % 10 == 1)
                count++;
            n /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndN(3141592));
        System.out.println(numberOf1Between1AndN_2(3141592));
    }
}
