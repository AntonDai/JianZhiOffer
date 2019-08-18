/**
 * @description: 不修改数组找出重复的数字
 * 思路1：创建一个长度为n+1的辅助数组，然后逐一把原数组的每个数字复制到辅助数组。
 * 如果原数组中被复制的数字是m,则把它复制到辅助数组中下标为m的位置。时间复杂度O(n),空间复杂度O(n)
 *
 * 思路2：把从1~n的数字从中间的数字m分为两部分，前面一半为1~m,后面一半为m+1~n，如果
 * 1~m的数字的数目超过m,那么这一半的区间里一定包含重复的数字；否则，另一半m+1~n的区间里
 * 一定包含重复的数字。继续把包含重复数字的区间一分为二，直到找到一个重复的数字。过程和二分查找类似，
 * 时间复杂度O(logn)*O(n)=O(nlogn),空间复杂度O(1)。
 * @author: Daniel
 * @create: 2019-02-14 21:23
 **/
public class DuplicationInArrayNoEdit {
    // 思路1
    public int getDuplication1(int[] numbers) {
        if(numbers == null || numbers.length == 0)
            return -1;
        int[] aux = new int[numbers.length]; // 辅助数组
        for(int i=0; i<numbers.length; i++) {// 数组长度为n+1,数组范围在1~n
            if (numbers[i] < 1 || numbers[i] > numbers.length - 1)
                return -1;
            int m = numbers[i];
            if(aux[m] == 0)
                aux[m] = m;
            else
                return m;
        }
        return -1;
    }

    // 思路2
    public int getDuplication2(int[] numbers) {
        if(numbers == null || numbers.length == 0)
            return -1;
        for(int i=0; i<numbers.length; i++) // 数组长度为n+1,数组范围在1~n
            if(numbers[i] < 1 || numbers[i] > numbers.length-1)
                return -1;
        int start = 1, end = numbers.length - 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1); // 注意括号，优先级 + >>
            int count = countRange(numbers,start,mid); // 计算在[start,mid]内数字的个数
            if(count > mid - start + 1)
                end = mid; // 二分查找时 end = mid - 1，这里只是过程和二分查找类似 [start,end] 中必然包含重复数字
            else
                start = mid + 1;
            if(start == end)
                return start;
        }
        return -1;
    }

    private int countRange(int[] numbers, int start, int end) {
        int count = 0;
        for(int i=0; i<numbers.length; i++) { // 时间复杂度O(n)
            if(numbers[i] >= start && numbers[i] <= end)
                count++;
        }
        return count;
    }

    public static void main(String[] args){
        int[] num1 = {};
        int[] num2 = {0,1,2,3,4};
        int[] num3 = {2,3,5,4,3,2,6,7};
        DuplicationInArrayNoEdit duplicationInArrayNoEdit = new DuplicationInArrayNoEdit();

        System.out.println(duplicationInArrayNoEdit.getDuplication1(num1));
        System.out.println(duplicationInArrayNoEdit.getDuplication1(num2));
        System.out.println(duplicationInArrayNoEdit.getDuplication1(num3));

        System.out.println(duplicationInArrayNoEdit.getDuplication2(num1));
        System.out.println(duplicationInArrayNoEdit.getDuplication2(num2));
        System.out.println(duplicationInArrayNoEdit.getDuplication2(num3));
    }
}
