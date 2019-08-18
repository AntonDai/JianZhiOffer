import java.util.Arrays;

/**
 * @description: 将数组中的0全部移动到末尾，要求保持非零元素的相对顺序
 * @author: Daniel
 * @create: 2019-03-12-21-32
 **/
public class MoveZeroToEnd {

    public static void moveZeros(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        for(int i=0; i<nums.length; i++) {
            for(int j=nums.length-1; j>i; j--) {
                if(nums[j] != 0 && nums[j-1] == 0)
                    swap(nums,j,j-1);
            }
        }
    }

    public static void moveZeros2(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        int i = 0, count = 0; // 统计0的个数
        for(int num : nums) {
            if(num != 0)
                nums[i++] = num;
            else
                count++;
        }
        for(int j=nums.length-count; j<nums.length; j++)
            nums[j] = 0;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        int[] nums = {1,0,9,0,4,5,0,4,2,0,1};
        moveZeros2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
