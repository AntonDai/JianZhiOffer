/**
 * @description: 表示数值的字符串
 * 若用正则表达式：[\\+-]?\\d*(\\.(\\d*))?(\\d+[eE][\\+-]?\\d+)?
 * 解释：符号部分[+-]?可能出现也可能不出现
 * 整数部分\d* 可以没有整数部分 .1 表示0.1
 * 小数部分 (\.\d*)?
 * 指数部分 (\d+[eE][\+-]?\d+)? 如果出现e|E 则后面必须要有整数,前面也必须要有数字
 * 注意，上面的表示是对于 单独的+-也是通过的，所以要另加判断
 * @author: Daniel
 * @create: 2019-02-19-21-19
 **/
public class NumericStrings {

    private int index = 0; // 记录扫描到的字符串的当前位置

//    public boolean isNumeric(String str) {
//        if(str == null || str.length() == 0)
//            return false;
//        if(str.equals("+") || str.equals("-"))
//            return false;
//        return str.matches("[\\+-]?\\d*(\\.(\\d*))?(\\d+[eE][\\+-]?\\d+)?");
//    }

    public boolean isNumeric2(char[] str) { // 这里的参数最好不要设置成字符串，因为在Java中每次返回的都是一个新的字符串
        if(str == null || str.length == 0)
            return false;
        index = 0; // 由于设置的index是成员变量，所以每次调用函数前都必须先初始化为0
        boolean numeric = scanInteger(str); // 是否有整数部分
        // 判断小数部分
        if(index < str.length && str[index] == '.') {
            index++;
            // 使用 || 是因为
            // 1. 小数点前可以没有数字 .12
            // 2. 小数点后可以没有数字 12.
            numeric = scanInteger(str) || numeric;
        }
        // 判断指数部分
        if(index < str.length && (str[index] == 'e' || str[index] == 'E')) {
            index++;
            // 使用 && 是因为
            // 1. e前面必须要有数字(包含整数和浮点数)
            // 2. e后面必须要有整数 3.14e+12
            numeric = numeric && scanInteger(str);
        }
        return numeric && index >= str.length;
    }

    // 判断是否有整数部分
    private boolean scanInteger(char[] str) {
        if(index < str.length && (str[index] == '+' || str[index] == '-'))
            index++;
        return scanUnsignedInteger(str);
    }

    // 判断是否有无符号整数部分
    private boolean scanUnsignedInteger(char[] str) {
        int temp = index;
        while(index < str.length && str[index] >= '0' && str[index] <= '9')
            index++;
        return index > temp;
    }

    public static void main(String[] args) {
        NumericStrings numericStrings = new NumericStrings();
        numericStrings.test("120".toCharArray(),true);
        numericStrings.test("-120.0".toCharArray(),true);
        numericStrings.test("+12e-3".toCharArray(),true);
        numericStrings.test(".123".toCharArray(),true);
        numericStrings.test("-1E+17".toCharArray(),true);
        numericStrings.test("+".toCharArray(),false);
        numericStrings.test("12.5e12".toCharArray(),true);
        numericStrings.test("12e.-3.4".toCharArray(),false);
        numericStrings.test("12.".toCharArray(),true); // 视为 12.0
        numericStrings.test(".e1".toCharArray(),false);
        numericStrings.test("e1".toCharArray(),false);
        numericStrings.test("12e".toCharArray(),false);
        numericStrings.test("12a3.e".toCharArray(),false);
        numericStrings.test("+.".toCharArray(),false);
        numericStrings.test("+".toCharArray(),false);
    }

    public void test(char[] str, boolean expected) {
        if(isNumeric2(str) == expected)
            System.out.println("passed");
        else
            System.out.println("failed");
    }
}
