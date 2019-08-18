package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 求众数2 https://leetcode-cn.com/problems/majority-element-ii/submissions/
 * @author: Daniel
 * @create: 2019-04-27 20:16:08
 **/
public class Solution229 {

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int num1 = 0, count1 = 0;
        int num2 = 0, count2 = 0;
        // 找出候选元素
        for (int x : nums) {
            if (x == num1) {
                count1++;
            } else if (x == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = x;
                count1 = 1;
            } else if (count2 == 0) {
                num2 = x;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        System.out.println("num1 : " + num1 + ", num2 : " + num2);
        count1 = 0; count2 = 0;
        // 确定候选元素是否是想要找的元素
        for (int x : nums) {
            if (x == num1)
                count1++;
            else if (x == num2)
                count2++;
        }
        if (count1 > nums.length / 3)
            res.add(num1);
        if (count2 > nums.length / 3)
            res.add(num2);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {8, 8, 7, 7, 7}));
        System.out.println(majorityElement(new int[] {2, 2}));
        System.out.println(majorityElement(new int[] {2, 2, 3}));
    }
}
