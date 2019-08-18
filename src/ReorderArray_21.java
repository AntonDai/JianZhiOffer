import java.util.Arrays;

/**
 * @description: 调整数组的顺序使奇数位于偶数前面，要求保持稳定性
 * 思路：可以利用冒泡、插入、归并排序的设计
 * @author: Daniel
 * @create: 2019-03-03-16-48
 **/
public class ReorderArray_21 {
    // 思路一：冒泡，前偶后奇就调整 O(n^2)
    public void reOrderArray(int[] array) {
        if(array == null || array.length == 0)
            return;
        for(int i=array.length-1; i>0; i--) {
            for(int j=0; j<i; j++)
                if((array[j] % 2 == 0) && (array[j+1] % 2 != 0)) // 前偶后奇就交换
                    swap(array,j,j+1);
        }
    }

    // 思路二：插入 O(n~n^2) 与数组初始顺序有关
    public void reOrderArray2(int[] array) {
        if(array == null || array.length == 0)
            return;
        for(int i=1; i<array.length; i++) { // 假定第一个数是奇数
            for(int j=i; j>0; j--)
                if((array[j-1] % 2 == 0) && (array[j] % 2 != 0))
                    swap(array,j-1,j);
        }
    }

    // 思路三：归并排序
    public void reOrderArray3(int[] array) {
        if(array == null || array.length == 0)
            return;
        int[] aux = new int[array.length];
        int oddCount = 0;
        for(int i=0; i<array.length; i++) {
            if(array[i] % 2 != 0)
                oddCount++;
            aux[i] = array[i];
        }
        int odd = 0, even = oddCount;
        for(int i=0; i<aux.length; i++) {
            if(aux[i] % 2 != 0)
                array[odd++] = aux[i];
            else
                array[even++] = aux[i];
        }
    }

    // 思路四：双指针法，不能保证稳定性
    public void reOrderArray4(int[] array) {
        if(array == null || array.length == 0)
            return;
        int i = 0, j = array.length - 1;
        while(i < j) {
            // 向后移动i,直到遇到第一个偶数，偶数的二进制表示最后一位肯定是0
            while(i < j && !isEven(array[i]))
                i++;
            // 向前移动j,直到遇到第一个奇数
            while(i < j && isEven(array[j]))
                j--;
            if(i < j)
                swap(array,i,j);
        }
    }

    // 判断一个数是不是偶数
    private boolean isEven(int n) {
        return (n & 1) == 0;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        ReorderArray_21 reorderArray_21 = new ReorderArray_21();
        reorderArray_21.test1();
    }

    public void test1() {
        int[] array = {1,2,3,4,5,6,7};
        reOrderArray4(array);
        System.out.println(Arrays.toString(array));
    }
}
