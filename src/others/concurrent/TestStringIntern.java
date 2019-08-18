package others.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 测试String的Intern方法
 * @author: Daniel
 * @create: 2019-03-26-15-14
 **/
public class TestStringIntern {
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();
            for (int i = 0; ; i++) {
                System.out.println(i);
                list.add(String.valueOf("String" + i++).intern());
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
