/**
 * @description: 机器人的运动范围
 * @author: Daniel
 * @create: 2019-02-18-16-10
 **/
public class RobotMove_13 {

    public int movingCount(int k, int rows, int cols) {
        if(k < 0 || rows <= 0 || cols <= 0)
            return 0;
        boolean[][] flag = new boolean[rows][cols];
        return count(k,rows,cols,0,0,flag); // 从起点（0，0）出发
    }

    private int count(int threshold, int rows, int cols, int i, int j, boolean[][] flag) {
        // 递归终止条件
        if(i < 0 || i >= rows || j < 0 || j >= cols // 越界
                || getDigitSum(i) + getDigitSum(j) > threshold // 限制条件
                || flag[i][j]) // 已访问
            return 0;
        flag[i][j] = true;
        return count(threshold,rows,cols,i-1,j,flag) +
                count(threshold,rows,cols,i+1,j,flag) +
                count(threshold,rows,cols,i,j-1,flag) +
                count(threshold,rows,cols,i,j+1,flag) +
                1;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while(num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        RobotMove_13 robotMove13 = new RobotMove_13();
        robotMove13.test1();
        robotMove13.test2();
        robotMove13.test3();
    }

    public void test(String testId, int k, int rows, int cols, int expected) {
        if(movingCount(k,rows,cols) == expected)
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }

    // 方格多行多列
    public void test1() {
        test("test1",5, 10, 10, 21);
    }

    // 方格只有一行，机器人能到达部分方格
    public void test2() {
        test("test2",10,1,100,29);
    }

    // 方格只有一行，机器人能到达所有方格
    public void test3() {
        test("test3",10,1,10,10);
    }
}
