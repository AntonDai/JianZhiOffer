import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @description: 滑动窗口的最大值
 * @author: Daniel
 * @create: 2019-02-25-09-27
 **/
public class MaxInSlidingWindow_59 {
    public static ArrayList<Integer> maxInWindows(int[] nums, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0 || size <= 0 || size > nums.length)
            return list;
        int maxIndex = -1; // 记录窗口内最大元素的索引
        for(int i=0; i<nums.length - size + 1; i++) { // i表示窗口第一个元素的索引
            if(i > maxIndex) {// 最大值索引已过期，窗口已经滑过了，更新最大索引
                maxIndex = i;
                for (int j = i + 1; j < i + size; j++) {
                    if (nums[j] >= nums[maxIndex])
                        maxIndex = j;
                }
            } else { // 否则，由于窗口每次只滑过一个元素，比较该元素与最大值即可
                if (nums[i + size - 1] >= nums[maxIndex])
                    maxIndex = i + size - 1;
            }
            list.add(nums[maxIndex]);
        }
        return list;
    }

    public static ArrayList<Integer> maxInWindowsWithDeque(int[] nums, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0 || size <= 0 || size > nums.length)
            return list;
        Deque<Integer> deque = new ArrayDeque<>(); // 保存每个窗口最大值的下标
        for(int i=0; i<nums.length; i++) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) // 双端队列的末尾存放的是最新窗口的最大值下标，队首存放的是上一个窗口的最大值下标
                deque.pollLast();
            deque.offer(i); // 从队尾插入
            if(deque.peekFirst() == (i - size)) // 检查队首元素是否过期
                deque.pollFirst();
            if(i - size + 1 >= 0) // 有效的窗口，因为最开始插入前size-1个元素时，还没有达到窗口的大小
                list.add(nums[deque.peekFirst()]);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows(nums,3));
        System.out.println(maxInWindowsWithDeque(nums,3));
    }
}
