import java.util.Arrays;

/**
 * @description: 加一
 * 输入数组[2,3,4] ，输出加一后的数组[2,3,5]
 * 输入数组[9,9,9] ，输出加一后的数组[1,0,0,0]
 * @author: Daniel
 * @create: 2019-03-12-22-17
 **/
public class PlusOne {

    public static int[] plusOne(int[] nums) {
        for(int i=nums.length-1; i>=0; i--) {
            if(nums[i] < 9) {
                nums[i]++;
                return nums; // 加一后返回
            }
            nums[i] = 0; // 等于9，置零，将上一位加一后，直接返回
        }
        int[] newNums = new int[nums.length+1];
        newNums[0] = 1;
        return newNums;
    }

    public static void main(String[] args) {
        int[] nums = {1,8,9};
        plusOne(nums);
        System.out.println(Arrays.toString(nums));
    }
}
