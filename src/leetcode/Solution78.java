package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 子集 https://leetcode-cn.com/problems/subsets/
 * @author: Daniel
 * @create: 2019-04-21-15-36
 **/
public class Solution78 {
    // 思路一：类似于位运算，比如 000 ~ 111 1表示取该位的字符，0表示不取
    // 比如 nums = {1, 2, 3}
    // 000 []
    // 001 [1]
    // 010 [2]
    // ...
    // 111 [1, 2, 3]
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        int N = nums.length;
        for (int i = 0; i < Math.pow(2, N); i++) {
            ArrayList<Integer> list = new ArrayList<>();
            fillList(i, list, nums);
            result.add(list);
        }
        return result;
    }
    // 思路二：从前往后遍历数组，每次将原来结果集中的所有数组全部取出加上当前字符组成新的数组放进结果集（保留原来结果集中的数组），遍历完数组就得到了最后的结果
    // 比如 nums = {1, 2, 3}
    // []
    // [1]
    // [2] [1, 2]
    // [3] [1, 3] [2, 3] [1, 2, 3]
    // 下一行由上面的所有行加上当前字符组成
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        result.add(new ArrayList<>()); // 初始化一个空集
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) { // 取出原来的数组加上新的字符组成新的数组
                ArrayList<Integer> list = new ArrayList<>(result.get(j));
                list.add(nums[i]);
                result.add(list);
            }
        }
        return result;
    }

    private void fillList(int i, ArrayList<Integer> list, int[] nums) {
        String s = Integer.toBinaryString(i); // 这里我没有用位运算，而是直接用了整数的二进制字符串表示，注意，高位的0不会显示
        int k = -1;
        for (int j = s.length() - 1; j >= 0; j--) {
            k++;
            if(s.charAt(j) == '1')
                list.add(nums[k]);
        }
    }
    // 还是位运算的思路，这次直接使用位运算，从 000 加到 111
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        int len = nums.length;
        double N = Math.pow(2, len);
        int index = 0;
        while (index < N) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                int mask = 1 << i; // 以 len = 3 为例，mask = 1、10、100 分别表示每一位上的掩码
                if ((index & mask) != 0) // 检查每一位上是否为1，若至少有一位为1，运算结果就不为0，就添加该位对应的字符
                    list.add(nums[i]);
            }
            result.add(list);
            index++;
        }
        return result;
    }

    // 思路四：回溯法，每一位可取可不取
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        int len = nums.length;
        for (int i = 0; i <= len; i++) // 对于不同长度的数组，长度为 0 、1、2、...
            dfs1(nums, 0, result, new ArrayList<>(), i);
        return result;
    }
    // 思考下面两种dfs的过程
    private void dfs(int[] nums, int i, List<List<Integer>> result, List<Integer> list, int length) {
        if (list.size() == length) { // [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
            result.add(new ArrayList<>(list));
            return;
        } else {
            for (int j = i; j < nums.length; j++) {
                list.add(nums[j]);
                dfs(nums, j + 1, result, list, length);
                list.remove(list.size() - 1);
            }
        }
    }

    private void dfs1(int[] nums, int i, List<List<Integer>> result, List<Integer> list, int length) {
        if (i == length) { // [[], [1], [1, 2], [2], [1, 2, 3], [1, 3], [2, 3], [3]]
            result.add(new ArrayList<>(list));
            return;
        } else {
            for (int j = i; j < length; j++) {
                list.add(nums[j]);
                dfs1(nums, j + 1, result, list, length);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Solution78().subsets4(nums));
        for (int i = 0; i < Math.pow(2, nums.length); i++)
            System.out.println(Integer.toBinaryString(i)); // 0 1 10 11 100 101 110 111
    }
}

