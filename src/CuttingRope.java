/**
 * @description: 剪绳子,要求至少剪一刀
 * @author: Daniel
 * @create: 2019-02-18-16-44
 **/
public class CuttingRope {

    /**动态规划
     * f(n)=max(f(i)*f(n-i))
     * */
    public int maxAfterCutting(int n) {
        if(n <= 1)
            return 0;
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        int[] array = new int[n+1];
        array[0] = 0;
        array[1] = 1; // 长度为1的绳子
        array[2] = 2; // 长度为2的绳子
        array[3] = 3; // 长度为3的绳子

        int max = 0;
        for(int i=4; i<=n; i++) {
            max = 0;
            for(int j=1; j <= i/2; j++) { // j <= i/2 是因为后面的乘积是重复的，只是交换了顺序
                int k = array[j] * array[i-j];
                if(max < k)
                    max = k;
                array[i] = max;
            }
        }
        return array[n];
    }

    /**贪婪算法
     * 当n>=5时，尽可能多的剪长度为3的绳子；当剩下的绳子长度为4时，把绳子剪成两段长度为2的绳子
     * */
    public int maxAfterCutting2(int n) {
        if(n <= 1)
            return 0;
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        int length3 = n / 3;
        if(n - length3 * 3 == 1)
            length3--;
        int length2 = (n - length3 * 3) / 2;
        return (int)(Math.pow(3,length3)) * (int)(Math.pow(2,length2));
    }

    public static void main(String[] args) {
        CuttingRope cuttingRope = new CuttingRope();
        cuttingRope.test1();
        cuttingRope.test2();
        cuttingRope.test3();
        cuttingRope.test4();
    }

    public void test(int length, int expected) {
        if(maxAfterCutting(length) == expected)
            System.out.println("Passed");
        else
            System.out.printf("Failed");
    }

    public void test1() {
        test(1,0);
    }

    public void test2() {
        test(4,4);
    }

    public void test3() {
        test(2,1);
    }

    public void test4() {
        test(8,18);
    }
}
