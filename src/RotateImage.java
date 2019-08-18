import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @description: 将一个n*n的矩阵顺时针旋转90度
 * @author: Daniel
 * @create: 2019-03-11-09-00
 **/
public class RotateImage {

    // ***********************顺时针***************************
    //原始矩阵              转置矩阵              水平对称翻转
    //1  2  3               1  4  7                7  4  1
    //4  5  6    ==>        2  5  8       ==>      8  5  2
    //7  8  9               3  6  9                9  6  3
    //
    //***********************逆时针***************************
    //原始矩阵             水平对称翻转            转置矩阵
    //1  2  3               3  2  1                3  6  9
    //4  5  6    ==>        6  5  4       ==>      2  5  8
    //7  8  9               9  8  7                1  4  7
    public static void rotate(int[][] matrix) {
        // 先得到转置矩阵
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i=0; i<row; i++) { // 为甚么题目限定是n*n的矩阵，如果是n*m,那转置之后的维度就不一样了，变成了m*n，要用一个新的数组
            for(int j=i; j<col; j++) { // 注意这里j的初始值是i,如果是0的话，就交换了两次，结果还是原样
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 再水平翻转
        for(int i=0; i<row; i++) {
            for(int j=0; j<col/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col-j-1];
                matrix[i][col-j-1] = temp;
            }
        }
    }

    // 求一个m*n的矩阵的转置矩阵,若不是方阵，肯定不能原地转置，因为转置前后矩阵的维度不一样
    public static int[][] reverse(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] reverseMatrix = new int[col][row];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                reverseMatrix[j][i] = matrix[i][j];
            }
        }
        return reverseMatrix;
    }

    // 按矩阵的某一列排序
    // 7   3   5                                   2   4    6
    // 2   4   6      若按第1列进行排序，结果为     4    8    9
    // 4   8   9                                   7   3    5
    // 按矩阵的第k列（0<=k<col）进行排序
    public static void sort(int[][] matrix, int k) {
        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[k] < o2[k])
                    return -1;
                else if(o1[k] > o2[k])
                    return 1;
                else
                    return 0;
            }
        });
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数组行数和列数：");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        System.out.println("请输入数组元素：");
        int[][] matrix = new int[row][col];

        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                matrix[i][j] = scanner.nextInt();

        scanner.close();
        System.out.println("您输入的矩阵为：");
        print(matrix);
    }

    public static void test1() {
        int[][] num= {{1,2,3},
            {4,5,6},
            {7,8,9}};
        System.out.println("原始矩阵为：");
        print(num);
        rotate(num);
        System.out.println("顺势针旋转90度后的矩阵(方阵)：");
        print(num);
    }

    public static void test2() {
        int[][] num = {{1,2,3},
                {4,5,6},
                {7,8,9},
                {10,11,12}};
        System.out.println("原始矩阵为：");
        print(num);
        int[][] reverse = reverse(num);
        System.out.println("转置后的矩阵：");
        print(reverse);
        for(int i=0; i<reverse.length; i++)
            for(int j=0; j<reverse[0].length/2; j++)
                swap(reverse,i,j,i,reverse[0].length-j-1);
        System.out.println("顺时针旋转90度后的矩阵为：");
        print(reverse);
    }

    // 测试二维数组按某列进行排序
    public static void test3() {
        int[][] matrix = {
                {7,3,5},
                {2,4,6},
                {4,8,9}};
        System.out.println("原始矩阵为：");
        print(matrix);
        sort(matrix,0);
        System.out.println("按第一列排序后的矩阵为：");
        print(matrix);
    }

    // 交换matrix[x1][y1] 和 matrix[x2][y2]
    public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    // 打印二维矩阵
    public static void print(int[][] matrix) {
        for(int i=0; i<matrix.length; i++)
            System.out.println(Arrays.toString(matrix[i]));
        System.out.println();
    }
}
