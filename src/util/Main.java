package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 测试使用
 * @author: Daniel
 * @create: 2019-04-06-09-35
 **/
public class Main {

    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        printBoard(board);
        solveSudoku(board);
        printBoard(board);
    }

    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    for(char c = '1'; c <= '9'; c++) {
                        if(isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if(solve(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false; // 都尝试了，但无解
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, char c) {
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == c)
                return false;
            if(board[row][i] == c)
                return false;
            if(board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == c)
                return false;
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class UnionFind {
    int[] roots;
    int[] rank; // 可以理解为团体总人数
    int countOfRoot; // 大boss的数目

    public UnionFind(char[][] grid) {
        init(grid);
    }

    private void init(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        roots = new int[m * n];
        rank = new int[m * n];
//        Arrays.fill(roots, -1);
//        Arrays.fill(rank, 0); // 根节点的深度定为0
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    roots[i * n + j] = i * n + j; // 初始化root指向自己
                    countOfRoot++;
                }
            }
        }
    }

    private int find(int i) { // 寻找root，不带路径压缩
        if(roots[i] != i)
            roots[i] = find(roots[i]);
        return roots[i];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) { // 如果它们不是一个团体的话，就将它们连接起来
            if(rank[rootX] > rank[rootY])
                roots[rootY] = rootX;
            else if(rank[rootX] < rank[rootY])
                roots[rootX] = rootY;
            else {
                roots[rootY] = rootX;
                rank[rootX] += 1;
            }
            countOfRoot--;
        }
    }
}

