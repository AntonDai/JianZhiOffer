/**
 * @description: BitMap的变种2-BitMap
 * 一个int类型，占4个字节，在2-BitMap中可以表示16个数，表示全部int类型数据需要内存2^32*2bit=1G。时间复杂度O（n）
 * @author: Daniel
 * @create: 2019-02-21-21-46
 **/
public class TwoBitMap {
    private int[] map;
    private int capacity;  // 应该是输入序列的最大值+1，BitSet内部采用的是根据最大值动态增长的方法


    public TwoBitMap(int capacity) {
        this.capacity = capacity;
        map = new int[(capacity >> 4) + 1]; // 0 1 ~ capacity 括号，优先级 capacity >> 4 + 1 为 capacity >> 5
    }

    //	|    8 bit  |
    //	|00 00 00 00|  //映射3 2 1 0
    //	|00 00 00 00|  //表示7 6 5 4
    //	……
    //	|00 00 00 00|

    /**
     * 往BitMap中设置对应的数的个数
     * @param x  要添加的数
     * @param num 对应的个数
     */
    public void set(int x,int num){
        //获得bitMap的下标
        int m = x >> 4; // x / 16
        //获得对应的位置
        int n = x & 0x0F; // x % 16

        //将x对应位置上的数值先清零，但是要保证其他位置上的数不变
        map[m] &= ~((0x03 << (2*n)));
        System.out.println("x = " + x + ", count = " + num + ", m = " + m + ", n = " + n + ": " + Integer.toBinaryString(map[m]));
        //重新对x的个数赋值
        map[m] |= (num << (2*n)); // num & 3 等价为 num % 4
        System.out.println("        m = " + m + ", n = " + n + ": " + Integer.toBinaryString(map[m]));
        System.out.println();
    }


    /**
     * 获取x在BitMap中的数量
     * @param x
     * @return 00表示不存在，01表示出现一次，10表示出现2次，11表示多次或无意义
     */
    public int get(int x){
        int m = x >> 4;
        int n = x & 0x0F;
        return (map[m] & (0x03 << (2*n))) >> (2*n);
    }


    /**
     * 往BitMap中添加数
     * 如果x的个数大于3，则不再添加(2个bit最多只能表示到3: 00 01 10 11)
     * @param x
     */
    public void add(int x){
        int num = get(x);
        //只处理num小于3的
        if(num<3) {
            set(x, num + 1);
        }
    }

    public static void main(String[] args) {
        int[] array = {1,4,1,32,2,6,4,2,69,9,4,185,2};
        TwoBitMap twoBitMap = new TwoBitMap(256); // 容量应该是数组中最大的数字

        for(int i=0; i<array.length; i++)
            twoBitMap.add(array[i]);

        System.out.println("对BitMap中的所有数据排序:");
        for(int i=0;i<256;i++){
            if(twoBitMap.get(i) != 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("只出现一次的数据:");
        for(int i=0;i<256;i++){
            if(twoBitMap.get(i) == 1){
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("出现多次的数据：");
        for(int i=0; i<256; i++)
            if(twoBitMap.get(i) > 1)
                System.out.print(i + " ");
        System.out.println();
    }
}
