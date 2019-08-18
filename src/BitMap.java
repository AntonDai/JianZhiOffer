import java.util.BitSet;

/**
 * @description: 位图算法
 * 参考：https://blog.51cto.com/zengzhaozheng/1404108
 * @author: Daniel
 * @create: 2019-02-21-20-33
 **/
public class BitMap {
    // 保存数据
    private byte[] map;
    // 能够存储多少数据
    private int capacity;

    public BitMap(int capacity) {
        this.capacity = capacity;
        // 1byte能存储8个数据，那么capacity数据需要多少个byte呢，capacity/8+1,右移3位相当于除以8
        map = new byte[(capacity >> 3) + 1]; // 为什么要加1 因为第一个数是0,之后是1~capacity
    }

    public void add(int num) {
        // num/8得到byte[]的索引 定位桶
        int index = num >> 3;
        // num%8得到在byte[index]中的位置 得到状态位
        int position = num & 0x07;
        // 将1左移position后，那个位置自然就是1，然后和以前的数据做 | 运算，这样，那个位置就替换成1了
        map[index] |= 1 << position;
    }

    public boolean contain(int num) {
        // num/8得到byte[]的索引
        int index = num >> 3;
        // num%8得到在byte[index]中的位置
        int position = num & 0x07;
        // 将1左移position后，那个位置自然就是1，然后和以前的数据做 & 运算，判断是否为0即可
        return (map[index] & (1 << position)) != 0;
    }

    public void clear(int num) {
        // num/8得到byte[]的索引
        int index = num >> 3;
        // num%8得到在byte[index]中的位置
        int position = num & 0x07;
        // 将1左移position后，那个位置自然就是1，然后对其取反，再与当前值做 &，即可清除当前的位置了
        map[index] &= ~(1 << position);
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(100);
        bitMap.add(7);
        System.out.println("插入7成功");

        boolean isExist = bitMap.contain(7);
        System.out.println("7是否存在：" + isExist);
        System.out.println("清除7");
        bitMap.clear(7);
        isExist = bitMap.contain(7);
        System.out.println("7是否存在：" + isExist);

        // BitSet类测试
        test();
    }

    // java中对应BitMap的数据结构BitSet类
    public static void test() {
        int[] array = {1,2,3,22,0,3};
        BitSet bitSet = new BitSet(array.length);
        // 将数组装进bitmap
        for(int i=0; i<array.length; i++)
            bitSet.set(array[i],true);
        System.out.println(bitSet.size()); // 64
        System.out.println(bitSet.get(3)); // true
        System.out.println(bitSet.get(11)); // false
    }
}
