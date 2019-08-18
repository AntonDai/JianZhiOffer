import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description: 字典序排列算法
 * 一个全排列可看做一个字符串，字符串有前缀、后缀。
 * 生成给定全排列的下一个排列，所谓一个的下一个就是这一个与下一个之间没有其他的。
 * 这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
 *
 * 比如839647521是1—9的一个排列。1—9的排列最前面的是123456789，最后面的987654321，
 * 从右向左扫描若都是增的，就到了987654321，也就没有下一个了。否则找出第一次出现下降的位置。
 *
 * 算法分4步：
 * 1. 从右向左找到第一个小于右边数字的数字位置，下标为j（从左到右，从0开始计算下标）
 * 2. 从j+1处开始向右找到最后一个大于j处数字的数字位置，下标记为k
 * 3. 交换j、k处两个数字
 * 4. 倒序j处（不包括j处）后的所有数字
 * 这样就得到了给定排列的下一个排列
 *
 * @author: Daniel
 * @create: 2019-03-14-11-32
 **/
public class StringPermutation_38_2 {

    public ArrayList<String> permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if(str == null || str.length() == 0)
            return list;
        char[] chars = str.toCharArray(); // 因为后面涉及到交换，所以要先转换为数组
        Arrays.sort(chars);
        list.add(String.valueOf(chars));
        int len = str.length();
        while(true) {
            int j = len - 2;
            // 1. 从右往左找到第一个比右边的数小的数字所在位置
            while(j >= 0 && chars[j] >= chars[j+1]) // = 是为了含有相同的数字
                j--;
            // 如果从右向左扫描都是递增的，则说明已经没有下一个了，此时可以退出循环
            if(j < 0)
                break;
            int k = j + 1;
            // 2. 从j处开始向右找最后一个大于j处的数字所在位置，因为多执行了一次循环，应该是k-1处
            while(k < len && chars[k] > chars[j]) // 注意这里不能有等于
                k++;
            // 3. 交换j、k-1处
            swap(chars,j,k-1);
            // 4.倒序j后面的所有数字
            reverse(chars,j+1,len-1);
            list.add(String.valueOf(chars));
        }
        return list;
    }

    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public void reverse(char[] chars, int low, int high) {
        while(low < high) {
            swap(chars,low, high);
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        StringPermutation_38_2 stringPermutation_38_2 = new StringPermutation_38_2();
        String str = "abc";
        ArrayList<String> list = stringPermutation_38_2.permutation(str);
        System.out.println(list);
        String str2 = "aac";
        System.out.println(stringPermutation_38_2.permutation(str2));
    }
}
