import java.util.Arrays;

/**
 * @description: 快速排序
 * 算法：选取一个基准元素pivot，将数组切分（partition）为两个子数组，使得左子数组都比pivot小，右子数组都比pivot大，然后递推地切分子数组。
 * Quick Select不同于Quick Sort的是其没有对每个子数组做切分，而是对目标子数组做切分。其次，Quick Select与Quick Sort一样，是一个不稳定的算法；pivot的选取直接影响了算法的好坏
 * 最好情况下的时间复杂度O(nlogn);最坏情况下（即待排序的序列为正序或逆序）的时间复杂度为O(n^2);平均时间复杂度O(nlogn)
 * 最好情况下，递归树的深度为logn,空间复杂度为O(logn);最坏情况下需要进行n-1次递归调用，空间复杂度为O(n);平均空间复杂度O(logn),
 * 快速排序是不稳定的。
 * @author: Daniel
 * @create: 2019-02-17-09-31
 **/
public class QuickSort {
    private int partition(int[] nums, int low, int high) {
        int index = nums[low]; // 切分元素
        int i = low+1, j = high; // 左右扫描指针
        while(true) {
            // 从左向右扫描直到找到第一个大于切分元素的位置
            while(nums[i] <= index && i < high)
                i++;
            // 从右向左扫描直到找到第一个小于切分元素的位置
            while(nums[j] >= index && j > low)
                j--;
            if(i >= j)
                break;
            swap(nums,i,j);
        }
        // 最后将切分元素放入正确的位置
        swap(nums,low,j);
        return j; // 返回切分元素的位置
    }

    private int partition2(int[] nums, int low, int high) {
        int index = nums[low]; // 用第一个元素作为切分元素
        while(low < high) {
            // 下面两个循环的顺序不能变，先从右向左交换小的到低端，接着从左向右交换大的到高端，可以逐步缩小范围
            while(low < high && nums[high] >= index) // 将比切分元素小的交换到低端
                high--;
            swap(nums,low,high);
            while(low < high && nums[low] <= index) // 将比切分元素大的交换到高端
                low++;
            swap(nums,low,high);
        }
        return low;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sort(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        sort(nums,0,nums.length-1);
    }

    private void sort(int[] nums, int low, int high) {
        if(low < 0 || high > nums.length - 1 || low >= high)
            return;
        int index = partition2(nums,low,high);
        // 根据切分元素划分左右子数组，分别对子数组排序
        sort(nums,low,index-1);
        sort(nums,index+1,high);
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, nums.length - k + 1,0,nums.length - 1);
    }

    /**
     * QuickSelect的目标是找出第K小的元素
     * 若切分后的左子数组的长度 > k，则第k小的元素肯定出现在左子数组中
     * 若切分后的左子数组的长度 = k - 1，则第k小的元素为pivot
     * 若上述两个条件都不满足，则第k小的元素必出现在右子数组，并且在右子数组中为第k-(index-low+1)小
     * @param nums
     * @param k
     * @param low
     * @param high
     * @return
     */
    private int quickSelect(int[] nums, int k, int low, int high) {
        if(low >= high)
            return nums[low];
        int index = partition(nums,low,high);
        if(index - low + 1 > k)
            return quickSelect(nums,k,low,index-1);
        else if(index - low + 1 < k)
            return quickSelect(nums,k-(index-low+1),index+1,high);
        else
            return nums[index];
    }

    // 三向切分快速排序
    public void threeWayQuickSort(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        threeWayQuickSort(nums,0,nums.length-1);
    }

    private void threeWayQuickSort(int[] nums, int low, int high) {
        if(high <= low)
            return;
        int pivot = nums[low];
        int l = low, c = low + 1, r = high;
        while(c <= r) {
            if(nums[c] < pivot)
                swap(nums,l++,c++); // 注意这里是先完成交换，再加一
            else if(nums[c] > pivot)
                swap(nums,c,r--);
            else
                c++;
        }
        threeWayQuickSort(nums,low,l-1);
        threeWayQuickSort(nums,r+1,high);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.test1();
        quickSort.test2();
        quickSort.test3();
        quickSort.test4();

        // 测试三向切分排序
        int[] nums = {3,1,1,2,3,6};
        quickSort.threeWayQuickSort(nums);
        System.out.println("排序后的数组为：" + Arrays.toString(nums));
    }

    public void test(int[] nums, int[] sortNums) {
        if(nums.length != sortNums.length)
            throw new RuntimeException("Invalid input!");
        for(int i=0; i<sortNums.length; i++) {
            if(nums[i] != sortNums[i]) {
                System.out.println("Failed");
                return;
            }
        }
        System.out.println("Passed");
    }

    public void test1() {
        int[] nums = {3,1,6,2};
        int[] sortNums = {1,2,3,6};
        sort(nums);
        test(nums,sortNums);
    }

    public void test2() {
        int[] nums = {3,3,1,2,3,4,2,2};
        int[] sortNums = {1,2,2,2,3,3,3,4};
        sort(nums);
        test(nums,sortNums);
    }

    // 最坏的情况
    public void test3() {
        int[] nums = {1,2,3,4};
        int[] sortNums = {1,2,3,4};
        sort(nums);
        test(nums,sortNums);
    }

    public void test4() {
        int[] nums = {4,3,2,1};
        int[] sortNums = {1,2,3,4};
        sort(nums);
        test(nums,sortNums);
    }
}
