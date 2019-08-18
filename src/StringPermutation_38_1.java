
import java.util.ArrayList;

/**
 * @description: 字符的所有组合
 * @author: Daniel
 * @create: 2019-02-21-08-53
 **/
public class StringPermutation_38_1 {
    public ArrayList<String> combineRecursive(char[] str) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(str != null && str.length > 0)
            for(int i=1; i<=str.length; i++) // 输入n个字符，求长度为m(1<=m<=n)的组合
                combine(str,0,i,sb,list);
        return list;
    }

    public void combine(char[] str, int start, int length, StringBuilder sb, ArrayList<String> list) {
        if(length == 0) { // 是否已经达到了组合的长度
            list.add(sb.toString());
            return;
        }
        if(start == str.length){ // 到了字符串末尾
            return;
        }
        sb.append(str[start]); // 将第一个字符添加进组合
        combine(str,start+1,length-1,sb,list); // 组合中包含第一个字符，从剩下的n-1个字符中选取m-1个字符
        sb.deleteCharAt(sb.length() - 1); // 回溯，退到上一个状态
        combine(str,start+1,length,sb,list); // 组合中不包含第一个字符，在剩下的n-1个字符中选取m个字符
    }

    /**abc 各个位是否选取，一共有 2^n 个，0表示不选取，1表示选取，去掉000
     * 001 a
     * 010 b
     * 011 ab
     * 100 c
     * 101 ca
     * 110 cb
     * 111 abc
     * */
    public  ArrayList<String> combine(char[] str) {
        if(str == null || str.length == 0)
            return null;
        ArrayList<String> list = new ArrayList<>();

        int length = str.length;
        int nbits = 1 << length; // 2^n
        for(int i=0; i<nbits; i++) { // 先产生001 010 011 100 ... 111
            int n = 0;
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<length; j++) { // 再根据相应的状态位是否为1，来组装字符串
                n = 1 << j; // 用n来依次读取该数二进制表示的每一位
                if ((n & i) != 0) { // 如果第n位为1，添加进结果集
                    sb.append(str[j]);
//                    System.out.print(str[j]);
                }
            }
            if(sb.length() != 0) // 最后将组装后的字符串添加进结果集
                list.add(sb.toString());
//            System.out.println();
        }
        return list;
    }

    public static void main(String[] args) {
        StringPermutation_38_1 stringPermutation38_1 = new StringPermutation_38_1();
        ArrayList<String> list = stringPermutation38_1.combineRecursive("abc".toCharArray());
        System.out.println(list);
        System.out.println(stringPermutation38_1.combine("abc".toCharArray()));

    }
}
