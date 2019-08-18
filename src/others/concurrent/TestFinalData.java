package others.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 测试final变量和普通变量的区别
 * @author: Daniel
 * @create: 2019-03-03-12-17
 **/
public class TestFinalData {
    public static void main(String[] args) {
        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;
        System.out.println(a == c); // true
        System.out.println(a == e); // false

        String s1 = "Hello" + "World";
        String s2 = "Hello";
        String s3 = "World";
        System.out.println(s1 == "HelloWorld"); // true
        System.out.println((s2 + s3) == s1); // false
        System.out.println((s2 + s3) == "helloWorld"); // false

        final Integer x = 1;
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        // x++; // 编译出错
        System.out.println(x);
        System.out.println(list); // 1
        list.set(0, 2);
        System.out.println(list); // 2
    }
}
