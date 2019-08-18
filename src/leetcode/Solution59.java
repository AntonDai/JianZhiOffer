package leetcode;

import java.util.Arrays;

/**
 * @description: 螺旋矩阵2 https://leetcode-cn.com/problems/spiral-matrix-ii/
 * @author: Daniel
 * @create: 2019-04-21-23-04
 **/
public class Solution59 {
    public static int[][] generateMatrix(int n) {
        if (n < 1)
            return new int[0][0];
        int[][] res = new int[n][n];
        int c = 1, i = 0;
        while (c <= n * n) {
            for (int j = i; j < n - i; j++) // 从左到右，包括右上角
                res[i][j] = c++;
            for (int j = i + 1; j < n - i; j++) // 从上到下，包括右下角
                res[j][n - i - 1] = c++;
            for (int j = n - i - 2; j >= i; j--) // 从右到左，包括左下角
                res[n - i - 1][j] = c++;
            for (int j = n - i - 2; j > i; j--) // 从下到上，不包括左上角
                res[j][i] = c++;
            i++;
        }
        return res;
    }
    // 原始打印矩阵
    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            System.out.println(Arrays.toString(matrix[i]));
        System.out.println();
    }
    // 顺时针打印矩阵 https://leetcode-cn.com/problems/spiral-matrix/
    private static void printClockwise(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 先定义4个角
        int left = 0, right = col - 1;
        int top = 0, bottom = row - 1;
        while (left <= right && top <= bottom) { // 要有等号，因为可能出现单行单列
            for (int i = left; i <= right; i++)
                System.out.print(matrix[top][i] + " ");
            for (int i = top + 1; i <= bottom; i++)
                System.out.print(matrix[i][right] + " ");
            for (int i = right - 1; i >= left && top < bottom; i--) // 防止单行
                System.out.print(matrix[bottom][i] + " ");
            for (int i = bottom - 1; i > top && left < right; i--) // 防止单列
                System.out.print(matrix[i][left] + " ");
            left++; right--;
            top++; bottom--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = generateMatrix(4);
        print(matrix);
        printClockwise(matrix);
    }
}
