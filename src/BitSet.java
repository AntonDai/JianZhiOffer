/**
 * @description: 实现Java的内置类BitSet
 * @author: Daniel
 * @create: 2019-03-13-08-29
 **/
public class BitSet {

    int[] bitSet;

    public BitSet(int size) {
        bitSet = new int[(size >> 5) + 1]; // divide by 32 + 1 优先级！优先级！优先级！括号！括号！括号！！！！！！！
    }

    public boolean contain(int num) {
        int index = num >> 5; // 得到数组的索引
        int position = num & 0x1F; // 得到数字在bitSet[index]中的位置
        return (bitSet[index] & (1 << position)) != 0;
    }

    public void set(int num) {
        int index = num >> 5;
        int position = num & 0x1F;
        bitSet[index] |= (1 << position);
    }

    public static void main(String[] args) {
        int num = 32;
        BitSet bitSet = new BitSet(num); // 可以存储0~31
        bitSet.set(31);
        System.out.println(bitSet.contain(31));
    }
}
