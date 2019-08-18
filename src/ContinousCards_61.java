import java.util.Arrays;

/**
 * @description: 扑克牌中的顺子
 * @author: Daniel
 * @create: 2019-02-26-10-03
 **/
public class ContinousCards_61 {

    /**思路一：由于数组元素都在0~13之间，可以使用bitmap算法，用一个int来存储每一个元素，1表示存在，0表示不存在*/
    public static boolean isContinuous(int[] numbers) {
        if(numbers == null || numbers.length != 5)
            return false;
        int len = numbers.length;
        int min = 14, max = -1;
        int map = 0;
        for(int i=0; i<len; i++) {
            int num = numbers[i];
            if(num < 0 || num > 13) // num的取值范围是0~13
                return false;
            if(num == 0) // 遇到癞子，不往下执行，而是继续循环
                continue;
            if(((map >> num) & 1) == 1) // 遇到对子，肯定不是顺子
                return false;
            map |= (1 << num); // 将num所在的位置1
            if(num < min)
                min = num;
            if(num > max)
                max = num;
            if(max - min >= 5) // 这里已经跳过了0
                return false;
        }
        return true;
    }

    /**思路二：1. 先对数组进行排序
     *         2. 统计数组中0的个数，和相邻数字之间的空缺总数
     *         3. 如果数组中0的个数大于等于间隔的个数，则为顺子，否则就不是顺子
     *         值得注意的，如果数组元素有重复，则肯定不是顺子
     *         */
    public static boolean isContinuous2(int[] numbers) {
        if(numbers == null || numbers.length != 5)
            return false;
        Arrays.sort(numbers); // 先对数组排序
        int numberOfZero = 0, numberOfGap = 0;
        // 统计数组中0的个数
        for(int i=0; i<numbers.length && numbers[i] == 0; i++)
            numberOfZero++;
        // 统计排序数组中相邻元素之的空缺总数
        int small = numberOfZero;
        int big = small + 1;
        while(big < numbers.length) {
            if(numbers[small] == numbers[big]) // 有对子，不可能是顺子
                return false;
            numberOfGap += numbers[big] - numbers[small] - 1;
            small = big;
            big++;
        }
        return numberOfGap <= numberOfZero;
    }

    public static void main(String[] args) {
        int[] array = {0,1,2,3,6};
        System.out.println(isContinuous(array));
        System.out.println(isContinuous2(array));
    }
}
