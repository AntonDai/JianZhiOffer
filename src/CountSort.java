import java.util.Arrays;

/**
 * @description: 计数排序
 * @author: Daniel
 * @create: 2019-02-18-14-41
 **/
public class CountSort {

    public static void main(String[] args) {
        CountSort sort = new CountSort();
        sort.test1();
    }

    public void test1() {
        int[] array = {24,25,26,28,28,28,29,29,34,27,30,32};
        System.out.println("原始数组：" + Arrays.toString(array));
        sort(array);
        System.out.print("排序后的数组：");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void sort(int[] array) {
        int max = getArrayMax(array);
        int[] countArray = new int[max+1];
        for(int i=0; i<array.length; i++)
            countArray[array[i]]++;
        int k = 0;
        for(int i=0; i<countArray.length; i++)
            for(int j=0; j<countArray[i]; j++)
                array[k++] = i;
    }

    private int getArrayMax(int[] array) {
        int max = array[0];
        for(int i=1; i<array.length; i++) {
            if(max < array[i])
                max = array[i];
        }
        return max;
    }
}
