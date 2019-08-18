package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 单词搜索2
 * @author: Daniel
 * @create: 2019-04-15-10-31
 **/
public class Solution212 {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    Set<String> res = new HashSet<String>();
//
//    public List<String> findWords(char[][] board, String[] words) {
//        Trie trie = new Trie();
//        for(String word : words)
//            trie.insert(word);
//        int m = board.length;
//        int n = board[0].length;
//        boolean[][] visited = new boolean[m][n];
//        for(int i = 0; i < m; i++) {
//            for(int j = 0; j < n; j++) {
//                dfs(board, visited, "", i, j, trie);
//            }
//        }
//        return new ArrayList<>(res);
//    }
//
//    private void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie) {
//        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
//            return;
//        if(visited[i][j])
//            return;
//        str += board[i][j];
//        if(!trie.startsWith(str)) // 不是前缀，直接返回
//            return;
//        if(trie.search(str))
//            res.add(str);
//        visited[i][j] = true;
//        for(int k = 0; k < 4; k++)
//            dfs(board, visited, str, i + dx[k], j + dy[k], trie);
//        visited[i][j] = false;
//    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(String word : words)
            for(int i = 0; i < m; i++)
                for(int j = 0; j < n; j++)
                    if(board[i][j] == word.charAt(0))
                        dfs(board, word, 0, i, j, visited);
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, String word, int index, int r, int c, boolean[][] visited) {
        if(index == word.length()) {
            res.add(word); // set去重
            return;
        }
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length)
            return;
        if(visited[r][c])
            return;
        if(word.charAt(index) != board[r][c])
            return;

        visited[r][c] = true;
        for(int k = 0; k < 4; k++)
            dfs(board, word, index + 1, r + dx[k], c + dy[k], visited);
        visited[r][c] = false;

    }

    public static void main(String[] args) {
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {  {'o','a','a','n'},
                            {'e','t','a','e'},
                            {'i','h','k','r'},
                            {'i','f','l','v'}};
//        String[] words = {"a"};
//        char[][] board = {{'a'}};
        System.out.println(new Solution212().findWords(board, words));
    }
}

class Trie {
    class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];

        public TrieNode(char c) {
            val = c;
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode(' ');
    }

    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode(c);
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null)
                return false;
            cur = cur.children[c - 'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.children[c - 'a'] == null)
                return false;
            cur = cur.children[c - 'a'];
        }
        return true;
    }
}
