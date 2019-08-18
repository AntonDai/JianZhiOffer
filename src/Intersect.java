import java.util.Arrays;

/**
 * @description: 求两个数组的交集
 * @author: Daniel
 * @create: 2019-03-12-21-15
 **/
public class Intersect {

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] result = new int[len1 < len2 ? len1 : len2];
        int i = 0, j = 0, k = 0;
        while(i < len1 && j < len2) {
            if(nums1[i] == nums2[j]) {
                result[k++] = nums1[i];
                i++;
                j++;
            }else if(nums1[i] > nums2[j]) {
                j++;
            }else {
                i++;
            }
        }
        return Arrays.copyOfRange(result,0,k); // 部分拷贝
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        int[] nums1 = {3,4,2,6,7};
        int[] nums2 = {6,7,8,2,3,3,5,4};
        int[] intersect = intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect)); // 2,3,4,6,7
    }
}
