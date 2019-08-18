import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @description: 大数问题——判断两个数字是否相等
 * @author: Daniel
 * @create: 2019-02-19-11-33
 **/
public class BigNumber {
    public static void main(String[] args) {
        BigDecimal a, b;
        String command;
        System.out.println("请输入两个数字：");
        Scanner cin = new Scanner(System.in);

        while(cin.hasNext()) {
            a = cin.nextBigDecimal();
            b = cin.nextBigDecimal();

            if(a.compareTo(b) == 0)
                System.out.println("Yes");
            else
                System.out.println("No");

        }
    }
}
