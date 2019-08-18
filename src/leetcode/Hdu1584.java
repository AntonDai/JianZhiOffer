package leetcode;

import java.util.Scanner;

/**
 * @description: 蜘蛛牌 http://acm.hdu.edu.cn/showproblem.php?pid=1584
 * @author: Daniel
 * @create: 2019-04-27 10:29:39
 **/
public class Hdu1584 {

    private static int min = Integer.MAX_VALUE;  // 求最小值，初始化为最大值；求最大值，初始化为最小值
    private static int[] a = new int[11]; // 只有10张牌，所以取11
    private static int[] b = new int[11];
    private static boolean[] visited = new boolean[11];

    private static void dfs(int s, int k) {
        int[] c = new int[11]; // 用来标记本层变换位置的分值

        if (s == 9) {// 因为只能将牌拖动到比它大一的牌上面，所以最大的10不会动
            if (min > k)
                min = k;
            return;
        }

        for (int i = 1; i <= 9; i++) { // 只能移动编号为1到9的纸牌
            if (!visited[i]) {
                visited[i] = true;
                int kk = k + Math.abs(b[i] - b[i + 1]);
                int t = b[i]; // 纸牌大小为i时，它所对应的编号
                for (int j = 1; j <= 10; j++) { // 1->2 2->3 3->4 4->5 5->6 6->7 7->8 8->9 9->10
                    if (b[j] == t) { // 表示将大小为j(=i)的纸牌（对应的位置应该为b[j]）放到大小为i+1的纸牌上面
                        b[j] = b[i + 1]; // 它上面的所有分值的位置都要跟着变，将b[j]的位置更新为b[i+1]
                        c[j] = t; // 保留当前层的位置
                    }
                }
                dfs(s + 1, kk);
                // 下面是回溯
                visited[i] = false;
                for (int j = 1; j <= 10; j++) {
                    if (c[j] == t)
                        b[j] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine()); // 测试用例数
        int[] res = new int[t];
        int j = 0;
        while (t-- > 0) {
            for (int i = 1; i <= 10; i++) {
                a[i] = in.nextInt(); // 纸牌大小
                b[a[i]] = i; // 编号
            }
            min = Integer.MAX_VALUE;
            dfs(0, 0);
            res[j++] = min;
        }
        for (int i : res)
            System.out.println(i);
    }
}

// 测试用例
// 输入
//2
//1 2 3 4 5 6 7 8 9 10
//5 4 8 7 1 2 3 6 9 10
// 输出
// 9
// 19


