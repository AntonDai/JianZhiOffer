/**
 * @description: 数组中唯一只出现一次的数字 （其他数字都出现3次）
 * @author: Daniel
 * @create: 2019-02-24-09-28
 **/
public class NumberAppearingOnce_56_1 {
    public static int findNumAppearOnce(int[] nums) {
        if(nums == null || nums.length == 0)
            throw new IllegalArgumentException("invalid input");
        int[] bitSum = new int[32];
        for(int i=0; i<nums.length; i++) {
            int bitMask = 1;
            for(int j=31; j>=0; j--) { // 统计各位之和，从左到右第0位至第31位，第31位为最低位
                int bit = nums[i] & bitMask; // 先判断最低位是不是1
                if(bit != 0)
                    bitSum[j] += 1;
                bitMask = bitMask << 1;
            }
        }
        // 如果某一位的和能够被3整除，那么那个只出现一次的数字的二进制表示中对应的那一位为0，否则为1
        // 这样我们就得到了那个只出现一次的数字的二进制表示，将其转换为十进制即可
        int result = 0;
//        for(int i=0; i<32; i++) {
//            result <<= 1; // 转换成十进制
//            result += bitSum[i] % 3; // 各位之和是否能够被3整除
//        }
        for (int i = 0; i < 32; i++) {
            result += (bitSum[31- i] % 3) << i;
        }
        return result;
    }

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) { // 注意这里是 i < 32
            int nBit = 0;
            // 如果某一位的和能够被3整除，那么那个只出现一次的数字的二进制表示中对应的那一位为0，否则为1
            // 这样我们就得到了那个只出现一次的数字的二进制表示，将其转换为十进制即可
            for (int j = 0; j < nums.length; j++) {
                nBit += getBit(nums[j], i);
            }
            //
            nBit = nBit % 3;
            res += nBit << i; // 转换为10进制
        }
        return res;
    }

    // 取n的指定位
    private static int getBit(int n, int k) {
        return (n >> k) & 1; // ((n >> k) & 1) == 1 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] array = {8,2,3,3,2,3,2};
        System.out.println(findNumAppearOnce(array));
        System.out.println(singleNumber(array));
        System.out.println(findNumAppearOnce(new int[] {-2, 3, 3, 3}));
        System.out.println(singleNumber(new int[] {-2, 3, 3, 3}));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE)); //  2147483647 0x7FFFFFFF 01111111 11111111 11111111 11111111
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE)); // -2147483648 0x80000000 10000000 00000000 00000000 00000000
        System.out.println(Integer.toBinaryString(0));              //                         00000000 00000000 00000000 00000000
        System.out.println(Integer.toBinaryString(-1));             //   -1                    11111111 11111111 11111111 11111111
        System.out.println(Integer.toBinaryString(-8 >> 2)); // -2 0xFFFFFFFE 11111111 11111111 11111111 11111110 (符号位为1，负数)
        System.out.println(Integer.toBinaryString(-8 >>> 2)); // 0x3FFFFFFE   00111111 11111111 11111111 11111110 （符号位为0，正数）

//        char[] chars = "11111111111111111111111111111110".toCharArray(); // -2的补码
        char[] chars = "00000000000000000000000000001000".toCharArray(); // 正数的补码等于原码 8的补码
        System.out.println(chars.length); // 32
        int n = 0;
        for (int i = 0; i < 32; i++)
            n += (chars[31 - i] - '0') << i; // 从左到右 从最高位到最低位 第31位为最低位
        System.out.println(n);
    }
}
