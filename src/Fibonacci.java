import java.util.Arrays;

/**
 * @description: 斐波那契数列
 * @author: Daniel
 * @create: 2019-02-16-16-57
 **/
public class Fibonacci {
    public static int fibonacci(int n) {
        if(n <= 0) return 0;
        int first = 1;
        int second = 1;
        for(int i=3; i<=n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        return second;
    }

    public static double power(int base, int exp) { // 求解幂形如 x^n
        double res = 1;
        int n = exp >= 0 ? exp : -exp;
        while (n > 0) {
            if ((n & 1) == 1)
                res *= base;
            base *= base;
            n >>= 1;
        }
        return exp >= 0 ? res : (1 / res);
    }

    // 两个矩阵相乘
    public static int[][] product(int[][] A, int[][] B) {
        if(A[0].length != B.length)
            throw new UnsupportedOperationException();
        int m = A.length, n = B[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < A[0].length; k++)
                    res[i][j] += A[i][k] * B[k][j];
            }
        }
        return res;
    }

    // 求解矩阵幂
    public static int[][] matrixProduct(int[][] M, int n) {
        int m = M.length;

        int[][] res = init(m); // 单位阵

        while (n > 0) {
            if ((n & 1) == 1)
                res = product(res, M);
            M = product(M, M);
            n >>= 1;
        }
        return res;
    }

    private static int[][] init(int m) {
        int[][] E = new int[m][m];
        for (int i = 0; i < m; i++) {
            E[i][i] = 1;
        }
        return E;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public static int fibonacci2(int n) {
        if(n <= 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;
        int[][] E = {{1}, {1}};
        int[][] M = new int[2][2];
        M[0][0] = 1; M[0][1] = 1;
        M[1][0] = 1; M[1][1] = 0;
        return product(matrixProduct(M, n - 2), E)[0][0];
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 1}, {1, 0}};
//        int[][] matrix2 = {{2, 1}, {2, 4}};
//        int[][] matrix3 = {{2}, {2}};
//        int[][] product = product(matrix1, matrix3);
//        print(product);
        print(matrixProduct(matrix1, 3));
        for (int i = 1; i < 20; i++)
            System.out.println(fibonacci(i) == fibonacci2(i));
    }
}
