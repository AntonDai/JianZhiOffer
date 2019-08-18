package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: 切分大文件
 * @author: Daniel
 * @create: 2019-03-26-21-02
 **/
public class SplitFile {

    public static void getSplitFile(String src, String des, int count) {
        RandomAccessFile raf = null;
        try {
            /**
             * r 以只读方式打开指定文件
             * rw 以读写方式打开指定文件
             * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
             * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
             */
            raf = new RandomAccessFile(new File(src),"r");
            long length = raf.length(); // 目标文件的大小
            long maxSize = length / count; // 切分后每个文件的大小
            long offset = 0L; // 初始化偏移量
            for(int i = 0; i < count - 1; i++) { // 最后一份单独处理
                long begin = offset;
                long end = (i + 1) * maxSize;
                offset = getWrite(src,des,i,begin,end);
            }
            if(length - offset > 0) {
                getWrite(src,des,count-1,offset,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定文件每一份的边界，写入不同文件中
     * @param src 源文件
     * @param des 输出文件路径
     * @param index 源文件的顺序标识
     * @param begin 开始指针的位置
     * @param end 结束指针的位置
     */
    public static long getWrite(String src, String des, int index, long begin, long end) {
        String prefix = src.substring(src.lastIndexOf("/") + 1, src.lastIndexOf("."));
        String suffix = src.substring(src.indexOf("."));
        long endPointer = 0L;
        // 输入文件随机流
        RandomAccessFile in = null;
        // 输出文件随机流
        RandomAccessFile out = null;
        try {
            in = new RandomAccessFile(new File(src),"r");
            out = new RandomAccessFile(new File(des + File.separator + prefix + "_" + index + suffix),"rw");
            // 缓冲数组
            byte[] b = new byte[1024 * 1024]; // 1MB
            int n = 0;
            // 从指定位置读取文件字节流
            in.seek(begin);
            // 判断文件流读取的边界
            while(in.getFilePointer() <= end && (n = in.read(b)) != -1) { // 注意这里的顺序，应该是先判断，后读取
                // 根据每一份文件的范围，写入不同的文件
                out.write(b,0,n);
            }
            // 获取当前读取文件的指针
            endPointer = in.getFilePointer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null)
                    in.close();
                if(out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return endPointer;
    }

    /**
     * 文件合并
     * @param src 分割前的文件名
     * @param des 指定合并文件后的文件路径
     * @param count 文件个数
     */
    public static void merge(String src, String des, int count) {
        String path = src.substring(0, src.lastIndexOf("/")); // 获取文件路径
        String prefix = src.substring(src.lastIndexOf("/") + 1,src.lastIndexOf("_")); // 获取文件名
        String suffix = src.substring(src.indexOf(".")); // 获取后缀名
        RandomAccessFile rafR = null;
        RandomAccessFile rafW = null;
        try {
            // 写入文件
            rafW = new RandomAccessFile(new File(des + File.separator + prefix + suffix),"rw");
            // 合并文件
            for(int i = 0; i < count; i++) {
                // 读取文件
                rafR = new RandomAccessFile(new File(path + File.separator + prefix + "_" + i + suffix),"r");
                byte[] b = new byte[1024 * 1024];
                int n = 0;
                // 先读后写
                while((n = rafR.read(b)) != -1) { // 读
                    rafW.write(b,0,n); // 写
                }
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

    public static void main(String[] args) {
        String src = "D:/BaiduNetdiskDownload/我的文档/Hibernate实战.pdf"; // 目标文件的路径
        String savepath = "D:/file"; // 切分后文件的保存路径
        int count = 50;  // 切分的份数
        getSplitFile(src,savepath,count);
        merge("D:/file/hibernate实战_0.pdf",savepath,50);
    }
}
