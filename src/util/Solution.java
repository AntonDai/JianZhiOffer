package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 测试
 * @author: Daniel
 * @create: 2019-04-12-17-02
 **/
public class Solution {

//    public static void main(String[] args) {
//        List<String[]> results = solveNQueues(4);
//        for(int i = 0; i < results.size(); i++) {
//            for(String str : results.get(i))
//                System.out.println(str);
//            System.out.println();
//        }
//    }
//
//    public static List<String[]> solveNQueues(int n) {
//        List<String[]> res  = new ArrayList<>();
//        helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
//        return res;
//    }
//
//    /**
//     * @param r 行
//     * @param cols 列
//     * @param d1 主对角线 从左往右 以 N = 4 为例，（r-c）取值 -3 -2 -1 0 1 2 3 可进一步处理为(r-c+N) 1 2 3 4 5 6 7
//     * @param d2 次对角线 从右往左 以 N = 4 为例，(r+c)取值 0 1 2 3 4 5 6
//     * @param board 棋盘
//     * @param res 结果集
//     */
//    private static void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
//        if(r == board.length) // 放置完最后一行，添加进结果集
//            res.add(board.clone());
//        else {
//            for(int c = 0; c < board.length; c++) { // 枚举每一列
//                int id1 = r - c + board.length;
//                int id2 = r + c;
//                if(!cols[c] && !d1[id1] && !d2[id2]) {
//                    char[] row = new char[board.length];
//                    Arrays.fill(row, '.');
//                    row[c] = 'Q';
//                    board[r] = new String(row);
//                    cols[c] = true; d1[id1] = true; d2[id2] = true;
//                    helper(r + 1, cols, d1, d2, board, res);
//                    cols[c] = false; d1[id1] = false; d2[id2] = false;
//                }
//            }
//        }
//    }

//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> result = new ArrayList<>();
//        dfs(result, 0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new ArrayList<>());
//        return result;
//    }
//
//    private void dfs(List<List<String>> result, int r, boolean[] cols, boolean[] d1, boolean[] d2, List<String> list) {
//        if(r == cols.length) {
//            result.add(list);
//        }else {
//            for(int c = 0; c < cols.length; c++) {
//                int id1 = r - c + cols.length;
//                int id2 = r + c;
//                if(!cols[c] && !d1[id1] && !d2[id2]) {
//                    char[] row = new char[cols.length];
//                    Arrays.fill(row, '.');
//                    row[c] = 'Q';
//                    list.add(new String(row));
//                    cols[c] = true; d1[id1] = true; d2[id2] = true;
//                    dfs(result, r + 1, cols, d1, d2, new ArrayList<>(list));
//                    cols[c] = false; d1[id1] = false; d2[id2] = false;
//                    list.remove(list.size() - 1);
//                }
//            }
//        }
//    }

//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> result = new ArrayList<>();
//        boolean[][] board = new boolean[n][n];
//        dfs(board, 0, result);
//        return result;
//    }
//    // 先把整个棋盘放置好，将所有可以放置皇后的地方都标记出来，最后再遍历整个棋盘，依次放置皇后即可
//    private void dfs(boolean[][] board, int r, List<List<String>> result) {
//        int c = board[0].length;
//        for(int i = 0; i < c; i++) {
//            board[r][i] = true;
//            if(check(board, r, i)) {
//                if(r == c - 1) {
//                    List<String> list = new ArrayList<>();
//                    // 双重循环从上到下遍历整个棋盘，依次放置皇后
//                    for(int j = 0; j < c; j++) {
//                        StringBuilder sb = new StringBuilder();
//                        for(int k = 0; k < c; k++) {
//                            if(board[j][k])
//                                sb.append("Q");
//                            else
//                                sb.append(".");
//                        }
//                        list.add(sb.toString());
//                    }
//                    result.add(list);
//                }else {
//                    dfs(board, r + 1, result);
//                }
//            }
//            board[r][i] = false;
//        }
//    }
//
//    private boolean check(boolean[][] board, int r, int c) {
//        for(int i = 0; i < board.length; i++) { // 检查列
//            if(i != r && board[i][c])
//                return false;
//        }
//        for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) { // 检查次对角线
//            if(board[i][j])
//                return false;
//        }
//        for(int i = r - 1, j = c + 1; i >= 0 && j < board.length; i--, j++) { // 检查主对角线
//            if(board[i][j])
//                return false;
//        }
//        return true;
//    }

    private int count = 0;
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if(n < 1)
            return result;
        dfs(n, 0, 0, 0, 0, new ArrayList<>());
        return result;
    }

    private void dfs(int n, int row, int cols, int pie, int na, ArrayList<String> list) {
        if(row >= n) {
            result.add(new ArrayList<>(list));
            count++;
            return;
        }
        int bits = (~(cols | pie | na)) & ((1 << n) - 1); // 得到所有的空位，1表示可以放置皇后，后面与操作的目的是屏蔽掉高位的干扰，因为int有32位，而我们只需要最低的n位即可
        while(bits > 0) {
            int p = bits & -bits; // 取最低位的1，并在该位置放置皇后
            list.add(putQueue(p, n));
            dfs(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1, list); // 进入下一层
            list.remove(list.size() - 1);
            bits &= bits - 1; // 去掉最低位的1，该位置已经尝试放置过皇后了
        }
    }

    private String putQueue(int p, int n) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(p != 1) { // 找出应该在哪一列放置皇后
            i++;
            p >>= 1;
        }
        for(int j = 0; j < n; j++) {
            if(j == i)
                sb.append('Q');
            else
                sb.append('.');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solveNQueens(4));
    }
}
