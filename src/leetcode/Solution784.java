package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 字母大小写全排列 https://leetcode-cn.com/problems/letter-case-permutation/
 * @author: Daniel
 * @create: 2019-04-21-20-34
 **/
public class Solution784 {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if(S == null || S.length() == 0)
            return result;
        dfs(0, S.toCharArray(), result);
        return result;
    }

    private void dfs(int i, char[] chars, List<String> result) {
        if (i == chars.length) {
            result.add(new String(chars));
            return;
        } else {
            if (chars[i] >= '0' && chars[i] <= '9') {
                dfs(i + 1, chars, result);
            } else {
                if (chars[i] >= 'A' && chars[i] <= 'Z') { // A~Z 65 ~ 90
                    dfs(i + 1, chars, result);
                    chars[i] += 32; // 自动类型转换，相当于 (char)(chars[i] + 32)
                    dfs(i + 1, chars, result);
                } else { // a~z 97 ~ 122
                    dfs(i + 1, chars, result);
                    chars[i] -= 32;
                    dfs(i + 1, chars, result);
                }
            }
        }
    }
    // 上面的dfs改写
//    private void dfs(int i, char[] chars, List<String> result) {
//        if (i == chars.length) {
//            result.add(new String(chars));
//            return;
//        }
//
//        if (chars[i] >= '0' && chars[i] <= '9') {
//            dfs(i + 1, chars, result);
//            return;
//        }
//
//        if (chars[i] >= 'A' && chars[i] <= 'Z') {// A~Z 65 ~ 90
//            dfs(i + 1, chars, result);
//            chars[i] += 32; // 自动类型转换，相当于 (char)(chars[i] + 32)
//            dfs(i + 1, chars, result);
//        } else {// a~z 97 ~ 122
//            dfs(i + 1, chars, result);
//            chars[i] -= 32;
//            dfs(i + 1, chars, result);
//        }
//
//    }

    // https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
    public List<String> letterCasePermutation2(String S) {
        if (S == null || S.length() == 0)
            return new ArrayList<>();
        List<String> res = new ArrayList<>();
        dfs2(S.toCharArray(), 0, res);
        return res;
    }

    private void dfs2(char[] chars, int i, List<String> res) {
        if (i == chars.length) {
            res.add(new String(chars));
            return;
        }
        if (chars[i] >= '0' && chars[i] <= '9') {
            dfs2(chars, i + 1, res);
            return;
        }

        chars[i] = Character.toLowerCase(chars[i]);
        dfs2(chars, i + 1, res);

        chars[i] = Character.toUpperCase(chars[i]);
        dfs2(chars, i + 1, res);
    }

    // BFS
    public List<String> letterCasePermutation3(String S) {
        if (S == null || S.length() == 0)
            return new LinkedList<>();
        Queue<String> queue  = new LinkedList<>();
        queue.offer(S);

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i)))
                continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();

                chars[i] = Character.toUpperCase(chars[i]);
                queue.offer(String.valueOf(chars));

                chars[i] = Character.toLowerCase(chars[i]);
                queue.offer(String.valueOf(chars));
            }
        }

        return new LinkedList<>(queue);
    }

    public static void main(String[] args) {
        System.out.println(new Solution784().letterCasePermutation3("a1b2")); // [A1B2, A1b2, a1B2, a1b2]
    }
}
