import java.util.Random;

/**
 * @description: 洗牌算法 http://note.youdao.com/noteshare?id=e589b1c14b5d9f6a2f868dbeb1bb4c62&sub=58DFD5E891444CD9BFFFCC16131A4A51
 * 得到一个 M 以内的所有自然数的随机顺序数组，保证每一个数出现在所有位置上的概率是相等的。
 * @author: Daniel
 * @create: 2019-03-06-23-00
 **/
public class ShuffleArray {
    private int[] array;
    private int[] original;

    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public ShuffleArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone(); // 考虑场景：打乱->重置->打乱->...，保证原始数组引用不能逸出
        return original;
    }

    public int[] shuffle() {
        Random rand = new Random();
        for(int i=array.length-1; i>0; i--) {
            swap(i,rand.nextInt(i+1)); // nextInt()范围是[0,i+1) 左闭右开
        }
        return array;
    }
}
