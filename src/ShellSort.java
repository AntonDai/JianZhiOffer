import java.util.Arrays;

/**
 * @description: 希尔排序
 * @author: Daniel
 * @create: 2019-03-02-20-50
 **/
public class ShellSort {

    // 使用递增序列 1/2(3^k-1), k = 1, 2, ...
    public void sort(int[] nums) {
        int len = nums.length;
        int h = 1;
        while(h < len/3)
            h = 3 * h + 1; // 1, 4, 13, ...
        while(h >= 1) {
            for(int i=h; i<len; i++) { // 当h=1时，希尔排序就退化为插入排序
                for(int j=i; j>=h && nums[j] < nums[j-h]; j-=h)
                    swap(nums,j,j-h); // 交换逆序对
            }
            h = h / 3;
        }
    }
    /**插入排序即h=1时的希尔排序*/
    // 插入排序，每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
    public void insertSort(int[] nums) {
        int len = nums.length;
        for(int i=1; i<len; i++) { // 假定第一个数位置是正确的
            for(int j=i; j>=1 && nums[j] < nums[j-1]; j-=1)
                swap(nums,j,j-1);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.test1();
        shellSort.test2();
        shellSort.test3();
    }

    public void test1() {
        int[] nums = {5,11,7,9,2,3,12,8,6,1,4,10};
//        sort(nums);
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void test2() {
        int[] nums = {2,2,3,1,1,3};
//        sort(nums);
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void test3() {
        int[] nums = {3};
//        sort(nums);
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
