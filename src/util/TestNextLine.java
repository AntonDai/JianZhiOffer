package util;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: 测试nextLine与next
 * @author: Daniel
 * @create: 2019-04-16-15-21
 **/
public class TestNextLine {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int m = in.nextInt(); // 在缓冲区中遇到“空格”、“回车符”等空白字符时会将空白字符前的数据读取走，但空白字符不会被处理掉，光标仍然停留在本行
        int m = Integer.parseInt(in.nextLine());
        // 在缓冲区中读取一行数据，这行数据以“回车符”为结束标志，nextLine()会把包括回车符在内的数据取走，光标停留在下一行
//        in.nextLine(); // 去掉上一行的换行符
        while(m-- > 0) {
            int n = Integer.parseInt(in.nextLine());
            String s = in.nextLine();
            System.out.println(n + ": " + s);
        }
        in.close();

//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine(); // 读掉数值后面的换行符
//        String[] arr = new String[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] =  sc.nextLine();
//        }
//
//        System.out.println(Arrays.toString(arr));
    }
}

// 测试用例
// 7
// 3
// abc
// 4
// aaab
// 6
// abccde
// 3
// abb
// 8
// aaaabbcc
// 11
// aaaabbbccde
// 6
// aaabbc
// 注意，最后一个字符串的末尾必须要有换行符，否则不会输出最后一行。


