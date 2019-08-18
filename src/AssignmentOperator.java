import java.math.BigInteger;

/**
 * @description: 测试赋值运算符+=
 * @author: Daniel
 * @create: 2019-02-01 11:12
 **/
public class AssignmentOperator {
    public static void main(String[] args){
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println("a = " + a + ", b = " + b + ", a + b = " + sum);

        BigInteger c = new BigInteger(Integer.toString(Integer.MAX_VALUE));
        BigInteger d = new BigInteger(Integer.toString(Integer.MAX_VALUE));
        BigInteger e = c.add(d);
        System.out.println("c = " + c + ", d = " + d + ", c + d = " + e);
    }
}
