/**
 * @description: 正则表达式匹配
 * @author: Daniel
 * @create: 2019-02-19-17-07
 **/
public class RegularExpressionsMatching {

    public boolean match(char[] str, char[] pattern) {
        if(str == null || pattern == null)
            return false;
        return match(str,0,pattern,0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        if(j == pattern.length) // pattern 遍历完啦，看看str有没有遍历完
            return str.length == i;
        // 下一个字符是*
        if(j < pattern.length - 1 && pattern[j + 1] == '*') {
            if(i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) // 当前字符匹配
                return match(str,i,pattern,j + 2) // 忽略*,跳过pattern 比如 abc .*b
                        || match(str,i+1,pattern,j) // 保持当前状态，跳过str 比如 aaa a*
                        || match(str,i+1,pattern,j+2); // 匹配下一个状态 比如 abc a*b
            else // 当前字符不匹配
                return match(str,i,pattern,j+2); // abc b*abc
        }
        // 下一个字符不是*
        if(i < str.length && (str[i] == pattern[j] || pattern[j] == '.'))
            return match(str,i+1,pattern,j+1);
        return false; // 第二个字符不是*，当前字符不匹配，匹配失败
    }

    public static void main(String[] args) {
        RegularExpressionsMatching regularExpressionsMatching = new RegularExpressionsMatching();
        regularExpressionsMatching.test("".toCharArray(),"".toCharArray(),true);
        regularExpressionsMatching.test("".toCharArray(),".*".toCharArray(),true);
        regularExpressionsMatching.test("".toCharArray(),"c*".toCharArray(),true);
        regularExpressionsMatching.test("a".toCharArray(),".*".toCharArray(),true);
        regularExpressionsMatching.test("a".toCharArray(),"ab*".toCharArray(),true);
    }

    public void test(char[] str, char[] pattern, boolean expected) {
        if(match(str,pattern) == expected)
            System.out.println("passed");
        else
            System.out.println("failed");
    }
}
