import java.util.Scanner;

/**
 * @description: 有限状态机
 * https://blog.csdn.net/thisinnocence/article/details/41171991#commentBox
 * @author: Daniel
 * @create: 2019-04-16-10-41
 **/
public class BeautifulString {
    // 定义4个状态
    static char current; // 当前字符
    static int prevNum = 0; // 前一个字符的数量
    static int curNum = 0; // 当前字符的数量
    static int index = 0; // 当前字符是Beautiful String中的第几个元素

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nCase = in.nextInt(); // 案例的个数
        // 接下来是一个数字（字符串的长度），和一个字符串
        while(nCase-- > 0) {
            int n = in.nextInt();
            in.nextLine(); // 清除换行符
            boolean res = false;
            String s = in.nextLine();
            current = s.charAt(0);
            curNum = 1;
            index = 1;
            int i = 1;
            while (i < n) {
                if (current == s.charAt(i)) { // 当前字符与上一字符相等时
                    curNum++;
                    if(prevNum != 0 && curNum > prevNum) {
                        prevNum = 0;
                        index = 1;
                    }
                }
                else if (current == s.charAt(i) - 1) { // 当前字符为上一字符加一时
                    if (prevNum == 0 || curNum <= prevNum) { // 前一个字符数量比前前一个字符数量少
                        index++;
                    }else { // 前一个字符比前前一个字符数量大，抛弃前前一个字符
                        index = 2;
                    }
                    prevNum = curNum;
                    curNum = 1;
                }
                else {
                    index = 1;
                    curNum = 1;
                    prevNum = 0;
                }
                if (index >= 3 && curNum == prevNum) {
                    res = true;
                    break;
                }
                current = s.charAt(i);
                i++;
            }
            if (res)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        in.close();
    }
}
