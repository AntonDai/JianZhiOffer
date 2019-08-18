import java.nio.ByteOrder;

/**
 * @description: java中测试系统是大端还是小端模式
 *        long a = unsafe.allocateMemory(8);
 *
 *             unsafe.putLong(a, 0x0102030405060708L);
 *             byte b = unsafe.getByte(a);
 *             switch (b) {
 *             case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
 *             case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
 *             default:
 *                 assert false;
 *                 byteOrder = null;
 *             }
 * 先分配8个字节Long类型的内存，然后在内存中放入16进制0x0102030405060708L的数据，判断第一个字节是0x01还是0x08，如果是0x01，则说明大端字节顺序（高位在前，低位在后），如果是最大的0x08，则是小端字节顺序。
 *
 * @author: Daniel
 * @create: 2019-03-06-21-44
 **/
public class BigEndian {
    public static void main(String[] args) {
        if(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
            System.out.println("BIG_ENDIAN");
        else
            System.out.println("LITTLE_ENDIAN");
        System.out.println(0x0102L); // 0000 0001 0000 0010 = 258
    }
}
