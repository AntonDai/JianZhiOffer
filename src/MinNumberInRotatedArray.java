/**
 * @description: 旋转数组的最小数字
 * @author: Daniel
 * @create: 2019-02-18-15-09
 **/
public class MinNumberInRotatedArray {

    public static void main(String[] args) {
        MinNumberInRotatedArray minNumberInRotatedArray = new MinNumberInRotatedArray();
        minNumberInRotatedArray.test1();
        minNumberInRotatedArray.test2();
    }

    public void test(String testId, int actual, int expected) {
        if(actual == expected)
            System.out.println(testId + ": Passed!");
        else
            System.out.println(testId + ": Failed!");
    }

    public void test1() {
        int[] nums = {3,4,5,1,2};
        int min = minNumberInRotateArray(nums);
        test("test1",min,1);
    }

    public void test2() {
        int[] nums = {1,1,1,0,0,1};
        int min = minNumberInRotateArray(nums);
        test("test2",min,0);
    }

    public int minNumberInRotateArray(int[] array) {
        if(array == null || array.length == 0)
            return -1;
        int low = 0, high = array.length - 1;
        while(low < high) {
            int mid = low + ((high - low) >> 1);
            if(array[mid] > array[high]) // mid 属于前面的递增数组,此时最小值肯定在后面
                low = mid + 1;
            else if(array[mid] < array[high]) // mid 属于后面的递增数组，此时最小值肯定在前面，也有可能是mid
                high = mid;
            else // 无法判断，从后往前缩小范围
                high--;
        }
        return array[high];
    }
}
