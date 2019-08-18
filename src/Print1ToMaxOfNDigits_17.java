/**
 * @description: 打印从1到最大的n位数
 * @author: Daniel
 * @create: 2019-02-19-09-27
 **/
public class Print1ToMaxOfNDigits_17 {
    public void print1ToMaxNDigits(int n) {
        if(n <= 0)
            return;
        char[] nums = new char[n]; // 为什么这里不用int[]，因为用char[] 更节省空间，事实上用byte[] 更节省空间
        for(int i =0; i<nums.length; i++)
            nums[i] = '0';
        for(int i=0; i<10; i++) { // 在不足n位的数字前补0
            nums[0] = (char) (i + '0');
            print1ToMaxNDigitsRecursive(nums,n,0);
        }
    }

    public void print1ToMaxNDigits2(int n) {
        if(n <= 0)
            return;
        char[] nums = new char[n];
        // 必须先初始化字符数组为'0'，默认初始化为/u0000
        for(int i =0; i<nums.length; i++)
            nums[i] = '0';
        while(!incrementNumber(nums))
            printNumber(nums);
    }

    private void print1ToMaxNDigitsRecursive(char[] nums, int length, int index) {
        if(index == length - 1) {
            printNumber(nums);
            return;
        }
        for(int i=0; i<10; i++) {
            nums[index + 1] = (char) (i + '0');
            print1ToMaxNDigitsRecursive(nums,length,index+1);
        }
    }

    // 自加，并且判断什么时候到了最大的n位数
    private boolean incrementNumber(char[] nums) {
        boolean isOverflow = false; // 是否溢出
        int nTakeOver = 0; // 是否进位
        int length = nums.length;
        for(int i=length-1; i>=0; i--) {
            int nSum = nums[i]  - '0' + nTakeOver; // 这里要加上进位位
            if(i == length - 1) // 末尾自加1
                nSum++;
            if(nSum >= 10) { // 产生进位
                if(i == 0) // 到了最大的n位数——最低位产生了进位
                    isOverflow = true;
                else {
                    nSum -= 10; // 重新置成0
                    nTakeOver = 1; // 产生进位
                    nums[i] = (char) (nSum + '0');
                }
            } else { // 没有产生进位
                nums[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverflow;
    }

    // 从数组中第一个不为0的字符开始打印
    private void printNumber(char[] nums) {
        boolean isBeginning0 = true; // 是不是第一个零
        for(int i=0; i<nums.length; i++) {
            if(isBeginning0 && nums[i] != '0')
                isBeginning0 = false; // 一旦找到了第一个非零字符，就打印后面的所有字符
            if(!isBeginning0)
                System.out.print(nums[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Print1ToMaxOfNDigits_17 print1ToMaxOfNDigits17 = new Print1ToMaxOfNDigits_17();
//        print1ToMaxOfNDigits17.test(1);
//        print1ToMaxOfNDigits17.test(2);
//        print1ToMaxOfNDigits17.test(0);
//        print1ToMaxOfNDigits17.test(-1);
//        print1ToMaxOfNDigits17.test(11); // 超出int范围
        print1ToMaxOfNDigits17.incrementNumber("99".toCharArray());
    }

    public void test(int n) {
        print1ToMaxNDigits(n);
        print1ToMaxNDigits2(n);
    }
}
