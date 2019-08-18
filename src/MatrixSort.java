import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 对二维数组按某列进行排序
 * @author: Daniel
 * @create: 2019-03-11-10-07
 **/
public class MatrixSort {
    private static void sort(int[][] matrix, int[] order) {
        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for(int i=0; i<order.length; i++) {
                    int k = order[i];
                    if(o1[k] < o2[k])
                        return -1; // 按升序排列，若要按降序，改为1
                    else if(o1[k] > o2[k])
                        return 1;
                    else
                        continue;
                }
                return 0;
            }
        });
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 12, 34, 68, 32, 9, 12, 545 },
                { 34, 72, 82, 57, 56, 0, 213 },
                { 12, 34, 68, 32, 21, 945, 23 },
                { 91, 10, 3, 2354, 73, 34, 18 },
                { 12, 83, 189, 26, 27, 98, 33 },
                { 47, 23, 889, 24, 899, 23, 657 },
                { 12, 34, 68, 343, 878, 235, 768 },
                { 12, 34, 98, 56, 78, 12, 546 },
                { 26, 78, 2365, 78, 34, 256, 873 } };
        System.out.println("原始矩阵为：");
        print(matrix);
        sort(matrix,new int[]{0,1,2}); // 先根据第一列比较，若相同则再比较第二列,若第二列相同，则比较第三列
        System.out.println("按第一列排序后的矩阵为：");
        print(matrix);
    }

    private static void print(int[][] matrix) {
        for(int i=0; i<matrix.length; i++)
            System.out.println(Arrays.toString(matrix[i]));
        System.out.println();
    }
}

