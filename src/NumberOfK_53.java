/**
 * @description: 数字在排序数组中出现的次数
 * 由于是有序数组，很容易想到二分查找，由于k出现多次，我们找到了一个k,还要往它的左右顺序扫描，因为要查找的数字可能在长度为n的数组中出现O(n)次，
 * 所以这和从头到尾扫描数组的时间复杂度一样，都是O(n)。我们可以用二分查找找到第一个k，和最后一个k,这样的时间复杂度就为O(logn)
 * @author: Daniel
 * @create: 2019-02-23-17-27
 **/
public class NumberOfK_53 {
    public int getCountOfK(int[] nums, int k) {
        // k要在数组内
        if(nums == null || nums.length == 0 || k < nums[0] || k > nums[nums.length - 1])
            return 0;
        int firstIndex = getFirstK(nums,k);
        int lastIndex = getLastK(nums,k);
        int count = 0;
        if(firstIndex > -1 && lastIndex > -1)
            count = lastIndex - firstIndex + 1;
        return count;
    }

    // 寻找第一个k出现的位置
    public int getFirstK(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int mid = low + ((high - low) >> 1);
            if(nums[mid] < k)
                low = mid + 1;
            else if(nums[mid] > k)
                high = mid - 1;
            else if(mid - 1 >= 0 && nums[mid-1] == k)
                high = mid - 1;
            else
                return mid;
        }
        return -1; // 找不到
    }

    // 寻找最后一个k出现的位置
    public int getLastK(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int mid = low + ((high - low) >> 1);
            if(nums[mid] < k)
                low = mid + 1;
            else if(nums[mid] > k)
                high = mid - 1;
            else if(mid + 1 <= high && nums[mid+1] == k)
                low = mid + 1;
            else
                return mid;
        }
        return -1; // 找不到
    }

    public static void main(String[] args) {
        NumberOfK_53 numberOfK53 = new NumberOfK_53();
        numberOfK53.test1();
        numberOfK53.test2();
        numberOfK53.test3();
    }

    public void test1() {
        int[] array = {1,2,2,3,3,3,4};
        System.out.println(getCountOfK(array,3));
        System.out.println(getCountOfK(array,5));
    }

    public void test2() {
        int[] array = {1};
        System.out.println(getCountOfK(array,1));
    }

    public void test3() {
        int[] array = {1,2,2,3,3,3,4};
        System.out.println(getCountOfK(array,4));
        System.out.println(getCountOfK(array,1));
    }
}
