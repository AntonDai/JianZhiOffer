/**
 * @description: 和为s的两个数字
 * @author: Daniel
 * @create: 2019-02-24-10-26
 **/
public class TwoNumbersWithSum_57 {
    // num1，num2分别是长度为1的数组，用来存放这两个数字，Java是值传递
    public boolean findNumberWithSum(int[] array, int sum, int[] num1, int[] num2) {
        if(array == null || array.length == 0)
            return false;
        boolean isFind = false;
        int p1 = 0, p2 = array.length - 1;
        while(p1 < p2) { // 原数组是递增排序的
            long curSum = array[p1] + array[p2]; // 这里注意两个数字之和可能溢出
            if(curSum < sum)
                p1++;
            else if(curSum > sum)
                p2--;
            else {
                num1[0] = array[p1];
                num2[0] = array[p2];
                isFind = true;
                break;
            }
        }
        return isFind;
    }
}
