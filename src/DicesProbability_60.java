/**
 * @description: n个骰子的点数 参考 https://blog.csdn.net/k346k346/article/details/50988681
 * @author: Daniel
 * @create: 2019-02-25-12-02
 **/
public class DicesProbability_60 {
    // 返回点数和为sum的排列数
    public int getNSum(int n, int sum) {
        // 限制条件
        if(n <= 0 || sum < n || sum > 6 * n)
            return 0;
        // 初始条件
        if(n == 1)
            return 1;
        // 递推公式
        return getNSum(n-1,sum-1) + getNSum(n-1,sum-2) + getNSum(n-1,sum-3) +
                getNSum(n-1,sum-4) + getNSum(n-1,sum-5) + getNSum(n-1,sum-6);
    }

    public void getNSum2(int n) {
        if(n <= 0)
            return;
        int count = 5 * n + 1; // 6n - n + 1
        int[] countArray = new int[count];
        for(int i=0; i<6; i++) // n>=1,初始化n=1
            countArray[i] = 1;
        getNSum2(n,countArray);
        double total = Math.pow(6,n);
        for(int i=0; i<count; i++) {
            double ratio = countArray[i] / total;
            System.out.println("ratio = " + ratio);
        }
    }

    public void getNSum2(int n, int[] countArray) {
        for(int i=2; i<=n; i++) { // n=1已经初始化，n从2开始
            for(int s=6*i; s>=i; s--) {
                int temp1 = (s - 1 - (i - 1)) >= 0 ? countArray[s - 1 - (i - 1)] : 0;
                int temp2 = (s - 2 - (i - 1)) >= 0 ? countArray[s - 2 - (i - 1)] : 0;
                int temp3 = (s - 3 - (i - 1)) >= 0 ? countArray[s - 3 - (i - 1)] : 0;
                int temp4 = (s - 4 - (i - 1)) >= 0 ? countArray[s - 4 - (i - 1)] : 0;
                int temp5 = (s - 5 - (i - 1)) >= 0 ? countArray[s - 5 - (i - 1)] : 0;
                int temp6 = (s - 6 - (i - 1)) >= 0 ? countArray[s - 6 - (i - 1)] : 0;
                countArray[s-i] = temp1 + temp2 + temp3 + temp4 + temp5 + temp6;
            }
        }
//        System.out.println(Arrays.toString(countArray));
    }

    public void printProbability(int n) {
        double total = Math.pow(6,n);
        for(int i=n; i<=6*n; i++) {
            double ratio = getNSum(n, i) / total;
            System.out.println("sum = " + i + ", ratio = " + ratio);
        }
    }

    public static void main(String[] args) {
        DicesProbability_60 dicesProbability60 = new DicesProbability_60();
        dicesProbability60.printProbability(6);
        System.out.println("----------------------------");
        dicesProbability60.getNSum2(6);
    }
}
