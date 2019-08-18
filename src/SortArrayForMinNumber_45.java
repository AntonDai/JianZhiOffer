import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 把数组排成最小的数
 * @author: Daniel
 * @create: 2019-02-22-14-49
 **/
public class SortArrayForMinNumber_45 {
    public static String printMinNumber(int[] numbers) {
        if(numbers == null || numbers.length == 0)
            return "";
        int len = numbers.length;
        String[] strs = new String[len];
        // 将整型数组转为字符串数组
        for(int i=0; i<len; i++)
            strs[i] = String.valueOf(numbers[i]);
        Arrays.sort(strs, new Comparator<String>() { // 基本类型使用快速排序（对于基本类型，稳定性没有意义），引用类型使用归并排序（稳定性很重要）时间复杂度O(nlogn) 注意Jdk1.8基本类型使用DualPivotQuicksort，引用类型使用的是TimSort
            @Override
            public int compare(String o1, String o2) {
                String num1 = o1 + o2;
                String num2 = o2 + o1;
                return num1.compareTo(num2);
            }
        });
        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++)
            sb.append(strs[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3,32,321};
        System.out.println(printMinNumber(nums));
        test();
    }

    // 测试字符串的比较
    public static void test() {
        String str1 = "12345";
        String str2 = "165";
        System.out.println((str1 + str2).compareTo(str2 + str1)); // 拼接后的两个字符串长度肯定相等
    }
}
