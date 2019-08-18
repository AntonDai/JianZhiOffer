/**
 * @description: 数组中的逆序数
 * @author: Daniel
 * @create: 2019-02-23-11-49
 **/
public class InversePairs_51 {

    public int countInversePairs(int[] array) {
        if(array == null || array.length == 0)
            return 0;
        int[] aux = new int[array.length];
        return countInversePairs(array,aux,0,array.length - 1);
    }

    public int countInversePairs(int[] array, int[] aux, int low, int high) {
        if(low >= high)
            return 0;
        int mid = low + ((high - low) >> 1);
        int leftCount = countInversePairs(array,aux,low,mid);
        int rightCount = countInversePairs(array,aux,mid+1,high);
        int count = merge(array,aux,low,mid,high);
        return leftCount + rightCount + count;
    }

    // 原先的归并排序是从前往后复制（两个指针分别指向两个数组的开头，即每个数组的最小元素），但是这样的话，不好统计逆序数，如果第一个数组的某一个数大于后面数组的数，不一定大于后面数组的其他数
    // 改为从后往前复制，（两个指针分别指向两个数组的末尾，即每个数组的最大元素），这样，如果前一个数组的某个元素大于后面数组的某个元素，那么它也大于包括后一个数组当前元素在内的前面所有元素
    public int merge(int[] array, int[] aux, int low, int mid, int high) {
        int count = 0, i = mid, j = high;
        for(int k=low; k<=high; k++)
            aux[k] = array[k];
        for(int k=high; k>=low; k--){
            if(i < low) {
                array[k] = aux[j--];
            } else if(j <= mid) {
                array[k] = aux[i--];
            } else if(aux[i] > aux[j]) {
                count += j - mid;
                array[k] = aux[i--]; // 从后往前把大的数复制到数组中
            } else {
                array[k] = aux[j--];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        InversePairs_51 inversePairs51 = new InversePairs_51();
        int[] array = {7,5,6,4};
        System.out.println(inversePairs51.countInversePairs(array)); // 5
        int[] array1 = {1,2,3,4};
        System.out.println(inversePairs51.countInversePairs(array1)); // 0
        int[] array2 = {3,4,4,1,2};
        System.out.println(inversePairs51.countInversePairs(array2)); // 6
        int[] array3 = {2};
        System.out.println(inversePairs51.countInversePairs(array3)); // 0
        int[] array4 = {3,2};
        System.out.println(inversePairs51.countInversePairs(array4)); // 1
    }
}
