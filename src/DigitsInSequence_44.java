/**
 * @description: 数字序列中某一位的数字 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ...
 * @author: Daniel
 * @create: 2019-02-22-11-10
 **/
public class DigitsInSequence_44 {
    /**以第1001位数字7为例
     步骤1：首先确定该数字是属于几位数的;
     如果是一位数，n<9;如果是两位数，n<9+90*2=189;如果是三位数，n<189+900*3=2889;
     说明是三位数。
     步骤2：确定该数字属于哪个数。100+(1001-190)/3= 370。
     步骤3：确定是该数中哪一位。1001-190-(370-100)*3 = 1,所以位于“370”的下标为1的位置，即数字7(下标从0开始  )。
     */
    public static int digitAtIndex(int index) {
        if(index < 0) return -1;
        if(index < 10)  // 1 位数
            return index;
        int curIndex = 10, length = 2; // 2 位数
        int boundNum = 10; // 10 100 1000 10000 ...
        int step = lengthSum(length);
        while(curIndex + step < index) {
            curIndex += step;
            boundNum *= 10;
            length++;
            step = lengthSum(length);
        }
        int addNum = (index - curIndex) / length;
        int curNum = boundNum + addNum;
        return Integer.toString(curNum).charAt(index - curIndex - addNum * length) - '0';
    }

    public static int lengthSum(int length) {
        int count = 9;
        for(int i=1; i<length; i++) {
            count *= 10;
        }
        return count * length;
    }

    public static void main(String[] args) {
        for(int i=9; i<16; i++)
            System.out.println(digitAtIndex(i));
        System.out.println(digitAtIndex(1001));
    }
}
