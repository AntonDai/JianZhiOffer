/**
 * @description: 最长不含重复字符的子字符串
 * @author: Daniel
 * @create: 2019-02-22-20-45
 **/
public class LongestSubstringWithoutDup_48 {
    public static int longestWithoutDuplication(String str) {
        if(str == null || str.length() == 0)
            return 0;
        char[] chars = str.toCharArray();
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[26]; // 用来存储每个字符上次出现在字符串中的位置

        for(int i=0; i<26; i++)
            position[i] = -1;

        for(int i=0; i<str.length(); i++) { // 从左到右遍历每一个字符
            int preIndex = position[chars[i] - 'a'];
            // preIndex < 0 表示当前字符之前没有出现过
            // (d = i - preIndex) > curLength 表示当前字符出现在前一个对应子字符串之前
            if(preIndex < 0 || i - preIndex > curLength)
                curLength++; // f(i) = f(i-1) + 1
            else { // 当前字符之前出现过，两次所夹子字符串中没有重复的字符
                if(curLength > maxLength)
                    maxLength = curLength;
                curLength = i - preIndex; // f(i) = d
            }
            position[chars[i] - 'a'] = i;
        }
        return Math.max(curLength,maxLength); // 防止没有重复的字符串，比如 abcdefg
    }

    public static void main(String[] args) {
        System.out.println(longestWithoutDuplication("arabcacfr"));
        System.out.println(longestWithoutDuplication("abcdefg"));

    }
}
