import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 最小的k个数
 * 思路：堆排序的思路，适合处理海量数据，先遍历数组，将前k个数插入堆中，然后继续从输入数组中读入元素，将每次读入的元素
 * 与堆顶元素即最大值进行比较，若小于堆顶元素，则替换堆顶元素，否则就抛弃继续遍历下一个数，时间复杂度为O(nlogK)
 * 注意：求最小的k个数用最大堆，求最大的k个数用最小堆
 * @author: Daniel
 * @create: 2019-03-03-08-56
 **/
public class KLeastNumbers_40 {
    public ArrayList<Integer> getLeastNumbers(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return list;
        // 求最小的k个数，先构建一个最大堆，注意，java的PriorityQueue默认是最小堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        // 遍历数组,先构建大小为k的堆，然后每次将待加入的元素和堆顶的元素比较，若比堆顶的元素大，就抛弃，否则就替换掉堆顶的元素
        for(int i=0; i<nums.length; i++) {
            if(maxHeap.size() != k) // 先往堆中添加前k个元素
                maxHeap.offer(nums[i]);
            else if(maxHeap.peek() > nums[i]) {
                maxHeap.poll();
                maxHeap.offer(nums[i]);
            }
        }
        list.addAll(maxHeap);
        return list;
    }

    public static void main(String[] args) {
        KLeastNumbers_40 kLeastNumbers_40 = new KLeastNumbers_40();
        kLeastNumbers_40.test1();
    }

    public void test1() {
        int[] nums = {1,2,3,2,2,2,1};
        ArrayList<Integer> leastNumbers = getLeastNumbers(nums, 3);
        System.out.println(leastNumbers);
    }
}
