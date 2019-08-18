package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 全排列 https://leetcode-cn.com/problems/permutations/
 * @author: Daniel
 * @create: 2019-04-22 22:21:41
 **/
public class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs2(nums, 0, new ArrayList<>(), res); // 所有排列的长度都相等
        return res;
    }
    // 执行时间5ms，消耗内存38.7M
    private void dfs(int[] nums, int i, List<Integer> list, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(list));
        } else {
            for (int j = 0; j < nums.length; j++) {
                if (!list.contains(nums[j])) {
                    list.add(nums[j]); // 以 N = 3 为例，可以理解为有三个桶，每个桶中都可以放入3种不同的元素之一，先放一个桶，有3种选择，
                    dfs(nums, i + 1, list, res); // 再放下一个桶，有2种选择，最后放第3个桶，只有一种选择
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    // 执行时间29ms，消耗内存44.9M
    private void dfs2(int[] nums, int i, ArrayList<Integer> list, List<List<Integer>> res) {
        if (i == nums.length) {
            if (!res.contains(list))
                res.add(new ArrayList<>(list));
        } else {
            for (int j = i; j < nums.length; j++) {
                list.add(nums[j]);
                swap(nums, i, j);
                dfs2(nums, i + 1, list, res);
                list.remove(list.size() - 1);
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution46().permute(new int[] {1, 2, 3}));
    }
}
