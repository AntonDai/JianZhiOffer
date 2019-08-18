package others.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 测试Java内存溢出
 * @author: Daniel
 * @create: 2019-03-14-17-46
 **/
public class OOMTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024]; // 64K
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for(int i=0; i<num; i++) {
            // 稍作延时，令监控曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
