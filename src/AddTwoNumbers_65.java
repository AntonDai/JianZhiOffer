import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description: 不用加减乘除做加法
 * @author: Daniel
 * @create: 2019-02-27-15-07
 **/
public class AddTwoNumbers_65 {
    public static int add(int num1, int num2) {
        while(num2 != 0) {
            int temp = num1;
            num1 = num1 ^ num2;
            num2 = (temp & num2) << 1;
        }
        return num1;
    }

    // 交换两个数
    public static void swap(int[] num, int i, int j) {
        num[i] = num[i] + num[j];
        num[j] = num[i] - num[j];
        num[i] = num[i] - num[j];
    }

    public static void swap2(int[] num, int i, int j) {
        num[i] = num[i] ^ num[j];
        num[j] = num[i] ^ num[j];
        num[i] = num[i] ^ num[j];
    }

    public static void main(String[] args) {
        System.out.println("15 + 124 = " + add(15,124));

        int[] num = {1,2};
        System.out.println("原数组为：" + Arrays.toString(num));
        swap2(num,0,1);
        System.out.println("交换后的数组为：" + Arrays.toString(num));
    }
}
