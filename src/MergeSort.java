/**
 * @description: 归并排序
 * 算法：假设初始序列有n个元素，则可以看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，得到n/2(向上取整)个长度
 * 为2或1的有序子序列；再两两归并，... ，如此重复，直到得到一个长度为n的有序序列为止。
 * 每次扫描需要O(n),递归调用需要O(logn),最好、最坏、平均情况下的时间复杂度都为O(nlogn)
 * 自顶向下需要O(n)+递归调用栈O(logn)，空间复杂度O(n+logn)；自底向上避免了递归，空间复杂度O(n)
 * 归并排序需要两两比较，是稳定的。
 * @author: Daniel
 * @create: 2019-02-17-14-21
 **/
public class MergeSort {

    int[] aux;

    // 将有序的nums[l...m]和nums[m+1...h]归并为有序的nums[l...h]
    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1;
        for(int k=l; k<=h; k++)
            aux[k] = nums[k];
        for(int k=l; k<=h; k++) {
            if(i > m) // 左半边用尽，取右半边的元素
                nums[k] = aux[j++];
            else if(j > h) // 右半边用尽，取左半边的元素
                nums[k] = aux[i++];
            else if(aux[j] < aux[i]) // 右半边的元素小于左半边的元素，取右半边的元素
                nums[k] = aux[j++];
            else // 右半边的元素大于等于左半边的元素，取左半边的元素
                nums[k] = aux[i++];
        }
    }
    // 这种写法很适合统计逆序对
    private void merge2(int[] nums, int l, int m, int h) {
        int i = m, j = h;
        for(int k=l; k<=h; k++)
            aux[k] = nums[k];
        for(int k=h; k>=l; k--) {
            if(i < l)
                nums[k] = aux[j--];
            else if(j <= m)
                nums[k] = aux[i--];
            else if(aux[i] > aux[j])
                nums[k] = aux[i--];
            else
                nums[k] = aux[j--];
        }
    }

    /** 自顶向下归并排序*/
    public void sort(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        aux = new int[nums.length];
        sort(nums,0,nums.length-1);
    }

    private void sort(int[] nums, int low, int high) {
        if(high <= low)
            return;
        int mid = low + ((high - low) >> 1);
        sort(nums,low,mid);
        sort(nums,mid+1,high);
        merge(nums,low,mid,high);
    }

    /** 自底向上归并排序*/
    public void sort2(int[] nums) {
        int n = nums.length;
        aux = new int[n];
        for(int i=1; i<n; i+=i) // i表示子数组的大小，1,2,4,8，...
            for(int j=0; j<n-i; j+=2*i) // j表示子数组的索引，第一个子数组、第二个子数组、...
                merge2(nums,j,j+i-1,Math.min(j+2*i-1,n-1)); // 最后一个子数组的大小只有在数组长度n是i的偶数倍时才会等于i
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.test1();
        mergeSort.test2();
        mergeSort.test3();
        mergeSort.test4();
        mergeSort.test5();
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
//        sort(nums);
        sort2(nums);
        test(nums,sortNums);
    }

    public void test2() {
        int[] nums = {3,3,1,2,3,4,2,2};
        int[] sortNums = {1,2,2,2,3,3,3,4};
//        sort(nums);
        sort2(nums);
        test(nums,sortNums);
    }

    public void test3() {
        int[] nums = {1,2,3,4};
        int[] sortNums = {1,2,3,4};
//        sort(nums);
        sort2(nums);
        test(nums,sortNums);
    }

    public void test4() {
        int[] nums = {4,3,2,1};
        int[] sortNums = {1,2,3,4};
//        sort(nums);
        sort2(nums);
        test(nums,sortNums);
    }

    public void test5() {
        int[] nums = {3,4,5,1,2};
        int[] sortNums = {1,2,3,4,5};
        sort2(nums);
        test(nums,sortNums);
    }
}
