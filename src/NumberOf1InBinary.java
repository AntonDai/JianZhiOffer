/**
 * @description: 二进制中1的个数
 * 注意Java中的int都是有符号的，负数在计算机中是以补码存储的，求一个用二进制表示的负数的十进制表示，步骤是：减一，取反，原码
 * @author: Daniel
 * @create: 2019-02-18-19-36
 **/
public class NumberOf1InBinary {
    public int numberOf1_1(int n) {
        int count = 0;
        for(int i=0; i<32; i++) {
            if(((n >>> i) & 1) == 1)
                count++;
        }
        return count;
    }

    /**把一个整数减去一，再和原来的整数做与运算，会把该整数最右边的1变成0*/
    public int numberOf1_2(int n) {
        int count = 0;
        while(n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOf1InBinary numberOf1InBinary = new NumberOf1InBinary();
        numberOf1InBinary.test(0,0);
        numberOf1InBinary.test(10,2);
        numberOf1InBinary.test(Integer.MAX_VALUE,31); // 0x7fffffff
        numberOf1InBinary.test(-Integer.MAX_VALUE,32); // 0xffffffff
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE: " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("Integer.MIN_VALUE: " + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("-Integer.MAX_VALUE: " + Integer.toBinaryString(-Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(-8));
    }

    public void test(int num, int expected) {
//        int actual = numberOf1_1(num);
        int actual = numberOf1_1(num);
        if(actual == expected)
            System.out.println("passed");
        else
            System.out.println("failed");
    }

}
