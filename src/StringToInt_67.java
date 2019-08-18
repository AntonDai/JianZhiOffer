/**
 * @description: 把字符串转换为整数
 * 实现Integer.valueOf(str)的功能
 * @author: Daniel
 * @create: 2019-03-01-08-24
 **/
public class StringToInt_67 {
    public static int valueOf(String str) {
        if(str == null || str.length() == 0)
            throw new IllegalArgumentException("invalid input");
        int result = 0, i = 0;
        char[] array = str.toCharArray();
        int sign = 1;
        if(array[0] == '+')
            i++;
        else if(array[0] == '-') {
            i++;
            sign = -1;
        }
        for(; i<str.length(); i++) {
            int num = array[i] - '0';
            if(num > 9 || num < 0)
                throw new IllegalArgumentException("invalid input");
            if((Integer.MAX_VALUE - num) / 10 < result)
                throw new RuntimeException("input is too big");
            result = result * 10 + num;
        }
        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(valueOf("124") == 124);
        System.out.println(valueOf("0123") == 123);
//        System.out.println(valueOf("32e6"));
        System.out.println(valueOf("+324") == 324);
        System.out.println(valueOf("-324") == -324);
//        System.out.println(valueOf(String.valueOf(Integer.MAX_VALUE) + "1"));

//        System.out.println(Integer.valueOf("0123"));
//        System.out.println(Integer.valueOf("")); // NumberFormatException
//        System.out.println(Integer.valueOf("32e6")); // NumberFormatException
//        System.out.println(Integer.valueOf("+234"));
//        System.out.println(Integer.valueOf("-45"));
        System.out.println(Integer.valueOf(String.valueOf(Integer.MAX_VALUE) + "1"));
    }

}
