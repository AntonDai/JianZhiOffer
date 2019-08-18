/**
 * @description: 堆排序
 * 堆的定义：堆是具有如下性质的完全二叉树：每个节点的值都大于或等于其左右孩子节点的值，称为大顶堆；
 * 每个节点的值都小于或等于其左右孩子节点的值，称为小顶堆。
 * 最好、最坏、平均时间复杂度都为O(nlogn),空间复杂度O(1)
 * 由于比较和交换是跳跃式进行的，因此不稳定
 *
 * 把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，那么就可以得到一个从尾到头的递减序列，从正向来看就是一个递增序列，这就是堆排序。
 * @author: Daniel
 * @create: 2019-02-17-15-35
 **/
public class HeapSort {
    public void sort(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        int n = nums.length;
        // 由于是堆是完全二叉树，所以剩下的一半都是叶子节点，我们只需要调整根节点，遍历一半就可以啦
        for(int i=n/2; i>=1; i--) // 构建一个最大堆
            sink(nums,i,n); // 注意这里的i理解为节点的标号
        // 逐步将每个最大值的根节点与末尾元素交换，并且再次调整堆
        for(int j=n; j>1; j--) {
            // 交换堆顶元素(最大元素)与最后一个元素
            swap(nums,0,j-1); // 注意数组的起始下标为0，而我们设定的元素起始编号是1，这样方便些，当前节点的序号是i,它的左孩子一定是2i,右孩子一定是2i+1
            sink(nums,1,j-1); // 调整堆，最后一个元素已经是最大的了，不需要调整
        }
    }

    // 下沉操作
    private void sink(int[] nums, int i, int m) {
        while(2*i <= m) {
            int j = 2 * i;
            if(j < m && nums[j-1] < nums[j]) // 找到左右节点中大的那个节点位置，节点标号是2i和2i+1， j<m 说明不是最后一个节点,最后一个节点的编号是m
                j++;
            if(nums[i-1] >= nums[j-1]) // 将根节点与大的孩子节点比较，如果根节点小，就需要做调整
                break;
            swap(nums,i-1,j-1); // 这里输入的是数组的下标，对应的节点编号是i,2i,即交换第i和第2i个节点
            i = j; // 是否需要继续调整堆，交换后还可能比子节点小
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.test1();
        heapSort.test2();
        heapSort.test3();
        heapSort.test4();
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
        int[] nums = {3,1,6,2,5,4};
        int[] sortNums = {1,2,3,4,5,6};
        sort(nums);
        test(nums,sortNums);
    }

    public void test2() {
        int[] nums = {3,3,1,2,3,4,2,2};
        int[] sortNums = {1,2,2,2,3,3,3,4};
        sort(nums);
        test(nums,sortNums);
    }

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

    /**补充一个上浮操作，用来向堆中插入元素，即将新元素添加到数组末尾，然后上浮至合适的位置*/
    private void swim(int[] nums, int i) {
        while(i > 1 && nums[i/2] < nums[i]) { // i>1 说明不是第一个节点
            swap(nums,i/2-1,i-1);
            i = i / 2;
        }
    }
}
