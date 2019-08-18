/**
 * @description: 矩阵中的路径
 * @author: Daniel
 * @create: 2019-02-18-15-31
 **/
public class StringPathInMatrix_12 {

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix == null || matrix.length == 0 || str == null || str.length == 0 ||
                matrix.length != rows * cols || str.length > matrix.length)
            return false;
        boolean[] flag = new boolean[matrix.length];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++)
                // 遍历二维数组直到找到第一个等于str[0]的值，再递归判断其四周是否有符合条件的
                if(judge(matrix,i,j,rows,cols,flag,str,0))
                    return true;
        }
        return false;
    }

    // 参数 i j 表示待匹配元素在二维数组中的行坐标和列坐标
    private boolean judge(char[] matrix, int i, int j, int rows, int cols,
                          boolean[] flag, char[] str, int k) {
        int index = j + i * cols; // 传入的元素（二维坐标）在一维数组中的位置
        // 递归终止条件：三种情况
        if(i < 0 || j < 0 || i >= rows || j >= cols // 越界
                || matrix[index] != str[k]  // 字符不匹配
                || flag[index]) // 已经访问过了
            return false;
        if(k == str.length - 1) // 匹配到末尾了，说明之前的匹配都成功了
            return true;
        flag[index] = true;
        // 匹配下一个字符
        if(judge(matrix,i-1,j,rows,cols,flag,str,k+1) ||
                judge(matrix,i+1,j,rows,cols,flag,str,k+1) ||
                judge(matrix,i,j-1,rows,cols,flag,str,k+1) ||
                judge(matrix,i,j+1,rows,cols,flag,str,k+1))
            return true;
        // 运行到这里，说明没有找到匹配，回溯，试试其他的路径
        flag[index] = false;
        return false;
    }

    public static void main(String[] args) {
        StringPathInMatrix_12 stringPathInMatrix12 = new StringPathInMatrix_12();
        stringPathInMatrix12.test1();
        stringPathInMatrix12.test2();
        stringPathInMatrix12.test3();
    }

    public void test(String testId, char[] matrix, int rows, int cols, char[] str, boolean expected) {
        if(hasPath(matrix,rows,cols,str) == expected)
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }

    // ABTG
    // CFCS
    // JDEH

    // BFCE
    public void test1() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        char[] str = "BFCE".toCharArray();
        test("test1",matrix,3,4,str,true);
    }

    public void test2() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        char[] str = "ABFB".toCharArray();
        test("test2",matrix,3,4,str,false);
    }

    //ABCE
    //SFCS
    //ADEE

    //SEE
    public void test3() {
        char[] matrix = "ABCESFCSADEE".toCharArray();
        char[] str = "SEE".toCharArray();
        test("test3",matrix,3,4,str,true);
    }
}
