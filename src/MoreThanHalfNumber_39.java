/**
 * @description: 数组中出现次数超过一半的数字
 * @author: Daniel
 * @create: 2019-02-21-16-04
 **/
public class MoreThanHalfNumber_39 {

    // 注意，这种做法会修改原始数组
    public int moreThanHalfNum(int[] array) {
        if(array == null || array.length == 0)
            return -1;
        int low = 0, high = array.length - 1;
        int index = partition(array,low,high);
        int mid = array.length >> 1;
        while(index != mid) { // 次数超过数组长度的一半，排序后，肯定是中位数
            if(index > mid) {
                index = partition(array,low,index - 1);
            }else {
                index = partition(array,index + 1,high);
            }
        }
        int result = array[mid];
        // 检查该数出现频率是否超过数组长度一半
        if(!checkResult(array,result))
            result = -1;
        return result;
    }

    // 这种做法不修改原始数组
    public int moreThanHalfNum2(int[] array) {
        if(array == null || array.length == 0)
            return -1;
        int result = array[0];
        int times = 1;
        for(int i=1; i<array.length; i++) {
            if(result == array[i])
                times++;
            else
                times--;
            if(times == 0) {
                result = array[i];
                times = 1;
            }
        }
        if(!checkResult(array,result))
            result = -1;
        return result;
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[start];
        while(start < end) {
            while(start < end && array[end] >= pivot)
                end--;
            swap(array,start,end);
            while(start < end && array[start] <= pivot)
                start++;
            swap(array,start,end);
        }
        return start;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean checkResult(int[] array, int number) {
        int count = 0;
        for(int i=0; i<array.length; i++)
            if(array[i] == number)
                count++;
        if(count * 2 <= array.length)
            return false;
        return true;
    }

    public static void main(String[] args) {
        MoreThanHalfNumber_39 moreThanHalfNumber39 = new MoreThanHalfNumber_39();
        moreThanHalfNumber39.test();
    }

    public void test() {
        int[] array = {1,2,3,2,2,2,5,4,2};
//        int actual = moreThanHalfNum(array);
        int actual = moreThanHalfNum2(array);
        System.out.println(actual == 2);
    }
}
