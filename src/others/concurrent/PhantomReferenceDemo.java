package others.concurrent;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @description: 虚引用何时被回收
 * @author: Daniel
 * @create: 2019-03-20-15-33
 **/
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        String str = "hello";
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(str, queue);
        System.out.println(pr.get()); // null
    }
}
