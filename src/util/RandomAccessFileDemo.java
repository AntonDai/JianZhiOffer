package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: 随机文件读取
 * 使用多线程复制大文件
 * @author: Daniel
 * @create: 2019-03-27-10-55
 **/
public class RandomAccessFileDemo {
    static final int SIZE = 1024 * 1024;  // 线程复制块大小1MB

    public static void main(String[] args) {
        File f1 = new File("D:/BaiduNetdiskDownload/我的文档/Hibernate实战.pdf"); // 读入文件路径
        File f2 = new File("D:/file/hibernate.pdf"); // 写出文件路径

        if(!f1.exists()) {
            System.out.println("读入文件不存在");
            return;
        }

        RandomAccessFile rafR = null;
        RandomAccessFile rafW = null;
        try {
            rafR = new RandomAccessFile(f1, "r"); // 读入随机流
            // 不存在会自动创建
            rafW = new RandomAccessFile(f2, "rw"); // 写出随机流

            long length = rafR.length(); // 读入原文件大小
            rafW.setLength(length); // 设置目的文件大小

            int threadNum = (int) (length / SIZE); // 复制的线程数
            threadNum = (length % SIZE) == 0 ? threadNum : threadNum + 1;

            for (int i = 0; i < threadNum; i++) {
                new MyThread(f1, f2, i * SIZE).start();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rafR != null)
                    rafR.close();
                if(rafW != null)
                    rafW.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
// 自定义线程，实现对文件的随机读写
class MyThread extends Thread {
    private RandomAccessFile rafR; // 读取文件随机流
    private RandomAccessFile rafW; // 写入文件随机流
    private long startPoint;

    public MyThread(File fR, File fW, long startPoint) {
        try {
            this.rafR = new RandomAccessFile(fR,"r");
            this.rafW = new RandomAccessFile(fW,"rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.startPoint = startPoint;
    }

    @Override
    public void run() {
        try {
            rafR.seek(startPoint);
            rafW.seek(startPoint);
            byte[] b = new byte[1024];
            int len = 0;
            int maxSize = 0;
            while((len = rafR.read(b)) != -1 && maxSize < RandomAccessFileDemo.SIZE) {
                rafW.write(b,0,len);
                maxSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
