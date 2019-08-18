import java.util.ArrayList;

/**
 * @description: 顺时针打印矩阵
 * 思路是先固定4个角，然后依次从外向内遍历
 * @author: Daniel
 * @create: 2019-03-07-09-23
 **/
public class PrintMatrix_29 {
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return list;
        int row = matrix.length, col = matrix[0].length;
        int top = 0, left = 0, right = col - 1, bottom = row - 1;
        while(top<=bottom && left<=right) {
            // 从左到右
            for(int i=left; i<=right; i++)
                list.add(matrix[top][i]);
            // 从上到下
            for(int i=top+1; i<=bottom; i++)
                list.add(matrix[i][right]);
            // 从右往左
            for(int i=right-1; i>=left && top < bottom; i--) // 防止单行
                list.add(matrix[bottom][i]);
            // 从下往上
            for(int i=bottom-1; i>top && left < right; i--) // 防止单列
                list.add(matrix[i][left]);
            // 转向内圈
            top++;
            bottom--;
            left++;
            right--;
        }
        return list;
    }

    public static void main(String[] args) {
        test1();
    }

    // 1 2  3  4
    // 5 6  7  8
    // 9 10 11 12
    // 13 14 15 16
    public static void test1() {
        int[][] matrix = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}};
        ArrayList<Integer> integers = printMatrix(matrix);
        System.out.println(integers);
    }
}
