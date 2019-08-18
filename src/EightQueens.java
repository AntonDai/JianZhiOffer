import java.util.Arrays;

/**
 * @description: 八皇后问题
 * @author: Daniel
 * @create: 2019-02-21-12-10
 **/
public class EightQueens {
    private static final int N = 8;
    private static int count = 0;

    public static void show(int[][] chess) {
        System.out.println("第" + (++count) + "种摆法");
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++)
                System.out.print(chess[i][j] + " ");
            System.out.println();
        }
    }

    private boolean check(int[][] chess, int row, int col) {
        // 判断列
        for(int i=row; i>=0; i--) { // (0,col) (1,col) (2,col)
            if(chess[i][col] == 1)
                return false;
        }
        // 判断左上到右下的对角线
        for(int i=row, j=col; i>=0 && j>=0; i--, j--) { // (0,0) (1,1) (2,2)
            if(chess[i][j] == 1)
                return false;
        }
        // 判断右上到左下的对角线
        for(int i=row, j=col; i>=0 && j<chess.length; i--,j++) { // (0,7) (1,6) (2,5)
            if(chess[i][j] == 1)
                return false;
        }
        return true;
    }

    public void putQueenAtRow(int[][] chess, int row) {
        // 递归结束条件，放置到最后一个，成功
        if(row == N) {
            show(chess);
            return;
        }
        // 由于同一行不能放置两个皇后，因此从上到下每行放置一个，先放置在(0,0),然后放置在下一行第一列，检查，第二列。。。
        for(int i=0; i<N; i++) {
            // 把该行清零，防止有残余的皇后
            for(int j=0; j<N; j++)
                chess[row][j] = 0;
            if(check(chess,row,i)) {
                chess[row][i] = 1;
                putQueenAtRow(chess,row + 1); // 放置下一行
            }
        }
    }

    public void settleQueen() {
        int[][] chess = new int[N][N];
        putQueenAtRow(chess,0);
        count = 0; // 还原count
    }


    /**八皇后问题第二种解法*/
    public void settleQueen2() {
        int[] columnIndex = new int[N];
        for(int i=0; i<N; i++)
            columnIndex[i] = i; // 表示在第i行的column[i]列 ，肯定在不同列
        putQueenAtRow2(columnIndex, 0);
        System.out.println("一共有" + count + "种解法");
        count = 0;
    }

    public void putQueenAtRow2(int[] columnIndex, int i) {
        if(i == N - 1) {
            boolean flag = true;
            for(int j=0; j<N-1; j++) {
                for(int k=j+1; k<N; k++)
                if((k - j == columnIndex[k] - columnIndex[j]) || (j - k == columnIndex[k] - columnIndex[j])) // 检查对角线
                    flag = false;
            }
            if(flag) {
                System.out.println(Arrays.toString(columnIndex));
                count++;
            }
        }else {
            for(int j=i; j<N; j++) {
                swap(columnIndex,i,j);
                putQueenAtRow2(columnIndex,i+1);
                swap(columnIndex,i,j);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.settleQueen();
        eightQueens.settleQueen2();
    }
}
