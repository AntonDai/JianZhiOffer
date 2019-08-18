/**
 * @description: 数组中数字出现的次数（其他数字都出现偶数次）
 * @author: Daniel
 * @create: 2019-02-24-09-04
 **/
public class NumbersAppearOnce_56 {
    // num1,num2 分别是长度为1的两个数组，用来存储只出现一次的两个数，因为Java是值传递
    public void findNumsAppearOnce(int[] array, int[] num1, int[] num2) {
        if(array == null || array.length == 0)
            return;
        int num = 0, length = array.length; // 任何数与0异或的结果为它本身
        for(int i=0; i<length; i++)
            num ^= array[i];
        int index = findFirstBitIs1(num);
        for(int i=0; i<length; i++) {
            if(isBit1(array[i],index))
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }
    }

    // 寻找数字的二进制表示中最右边第一个为1的位置
    public int findFirstBitIs1(int num) {
        int index = 0;
        while((num & 1) == 0 && index < 32) {
            num >>= 1;
            index++;
        }
        return index;
    }

    // 判断数字的第n位是否为1
    public boolean isBit1(int num, int index) {
        return ((num >> index) & 1) == 1;
    }
}
