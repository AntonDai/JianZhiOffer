import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: 表达式计算
 * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，返回公式的计算结果。
 *
 * 【举例】
 *
 * str="48*((70-65)-43)+8*1"，返回-1816。
 *
 * str="3+1*4"，返回7。 str="3+(1*4)"，返回7。
 *
 * 【说明】
 *
 * 1．可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
 *
 * 2．如果是负数，就需要用括号括起来，比如"4*(-3)"。但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3)*4"都是合法的。
 *
 * 3．不用考虑计算过程中会发生溢出的情况
 * @author: Daniel
 * @create: 2019-03-29-12-13
 **/
public class ExpressionCompute {
    public static int getValue(String str) {
        return value(str.toCharArray(),0)[0];
    }

    public static int[] value(char[] str, int i) { // 注意，这里返回的是一个int[]数组
        Deque<String> deque = new LinkedList<>();
        int pre = 0; // 收集数字
        int[] bra = null; // value函数本身返回一个int[]数组，第一个元素表示计算的值，第二个元素表示指针的位置
        while(i < str.length && str[i] != ')') {
            if(str[i] >= '0' && str[i] <= '9') {
                pre = pre * 10 + str[i++] - '0';
            }else if(str[i] != '(') { // +-*/
                addNum(deque,pre);
                deque.addLast(String.valueOf(str[i++]));
                pre = 0; // 清0
            }else { // 当前位置为'('
                bra = value(str,i+1); // 不管，直接递归
                pre = bra[0];
            }
        }
        addNum(deque,pre);
        return new int[] {getNum(deque), i};
    }

    public static void addNum(Deque<String> deque, int num) {
        if(!deque.isEmpty()) {
            int cur = 0;
            String top = deque.pollLast();
            if(top.equals("+") || top.equals("-")) {
                deque.addLast(top); // 再放回去，是的队列中只有加减运算符和操作数
            }else { // */就计算完结果在放入队列中
                cur = Integer.valueOf(deque.pollLast()); // 从队列的尾部取出上一个数
                num = top.equals("*") ? (cur * num) : (cur / num); // 计算结果
            }
        }
        deque.addLast(String.valueOf(num)); // 将结果放入队列尾部
    }
    // 计算结果
    public static int getNum(Deque<String> deque) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!deque.isEmpty()) {
            cur = deque.pollFirst();
            if(cur.equals("+")) {
                add = true;
            }else if(cur.equals("-")) {
                add = false;
            }else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));
    }
}
