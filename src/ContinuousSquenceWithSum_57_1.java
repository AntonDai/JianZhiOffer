import java.util.ArrayList;

/**
 * @description: 和为s的连续正数序列
 * @author: Daniel
 * @create: 2019-02-24-10-40
 **/
public class ContinuousSquenceWithSum_57_1 {
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(sum < 3) // 至少含有两个数 1 2
            return result;
        int small = 1;
        int big = 2;
        int curSum = 3;
        // 这里没有用求和公式，使用求和公式很容易计算curSum
        while(small < big) {
            boolean flag = false;
            if(curSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=small; i<=big; i++)
                    list.add(i);
                result.add(list);
            }
            while(curSum > sum) {
                curSum -= small;
                small++;
                if(curSum == sum) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                big++;
                curSum += big;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findContinuousSequence(15));
    }
}
