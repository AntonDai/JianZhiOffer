import java.util.Arrays;

/**
 * @description: 合并两个排序数组
 * 思路：从尾到头比较两个数组中的数字，并将较大的数字复制到第一个数组中的合适位置
 * @author: Daniel
 * @create: 2019-02-15 14:31
 **/
public class MergeTwoSortedArray {

    public static void main(String[] args){
        int[] num1 = {1,3,5,0,0,0,0,0,0,0,0,0,0};
        int[] num2 = {1,2,4};
        int[] num3 = {2,4,5,7,9};
        MergeTwoSortedArray mergeTwoSortedArray = new MergeTwoSortedArray();
//        mergeTwoSortedArray.merge(num1,num2);
        mergeTwoSortedArray.merge(num1,num3);
        System.out.println(Arrays.toString(num1));
    }

    public void merge(int[] num1, int[] num2) {
        int len1 = 0;
        for(int i=0; i<num1.length; i++) {
            if(num1[i] == 0)
                break;
            len1++;
        }
        int len2 = num2.length;
        int p1 = len1 - 1, p2 = len2 - 1;
        int len = len1 + len2 - 1;
        while(p1 >= 0 && p2 >= 0) {
            if(num1[p1] >= num2[p2])
                num1[len--] = num1[p1--];
            else
                num1[len--] = num2[p2--];
        }
    }
}
