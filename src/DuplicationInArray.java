/**
 * @description: 数组中重复的数字
 * 思路：从头到尾依次扫描这个数组中的每个数字。当扫描到下标为i的数字时，首先比较这个数字（记为m)是否等于i。
 * 如果是，则接着扫描下一个数字；否则，拿它和数组中第m个数字进行比较。如果它和第m个数字相等，就找到了一个重复的数字
 * （该数字在下标为i和m的位置都出现了）；如果不相等，就把第i个数字和第m个数字进行交换，把m放入属于它的位置。重复这个过程，
 * 知道我们发现一个重复的数字。注意该方法修改了原始数组。
 * @author: Daniel
 * @create: 2019-02-14 20:32
 **/
public class DuplicationInArray {
    public boolean duplicate(int number[], int[] duplication) {
        // 检查空指针
        if(number == null || number.length == 0)
            return false;
        // 检查数组元素是否合法
        for(int i=0; i<number.length; i++)
            if(number[i] < 0 || number[i] > number.length-1)
                return false;
        // 虽然是一个二重循环，但每个数字最多只要交换两次就能找到属于它自己的位置，因此
        // 总的时间复杂度为O(n),空间复杂度为O(1)
        for(int i=0; i<number.length; i++) {
            while(number[i] != i) {
                int m = number[i];
                if(m == number[m]) {
                    duplication[0] = m;
                    return true;
                }
                // 和第m个数字不相等，把第i个数字和第m个数字交换
                number[i] = number[m];
                number[m] = m;
            }
        }
        return false; // 没有找到重复的数字
    }
    // 测试
    public static void main(String[] args){
        int[] num1 = {};
        int[] num2 = {0,1,2,2,5};
        int[] num3 = {0,1,2,3,4};
        int[] num4 = {2,3,1,0,2,5,3};
        int[] duplication = new int[1];
        DuplicationInArray duplicationInArray = new DuplicationInArray();
        System.out.println(duplicationInArray.duplicate(num1,duplication) + " " + duplication[0]);
        System.out.println(duplicationInArray.duplicate(num2,duplication) + " " + duplication[0]);
        System.out.println(duplicationInArray.duplicate(num3,duplication) + " " + duplication[0]);
        System.out.println(duplicationInArray.duplicate(num4,duplication) + " " + duplication[0]);
    }
}
