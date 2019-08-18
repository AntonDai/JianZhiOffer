package others.concurrent;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @description: 测试文件流不关闭会怎么样
 * FileInputStream 在使用完以后，不关闭流，想二次使用可以怎么操作
 * @author: Daniel
 * @create: 2019-03-06-19-57
 **/
public class TestInputStream {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File file = new File("D:/abc.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream("D:/a.txt");
        int len = 0;
        byte[] bytes = new byte[200*1024];
        while((len = inputStream.read(bytes)) != -1)
            outputStream.write(bytes,0,len);

//        if(inputStream.read() == -1) {
//            Class in = inputStream.getClass();
//            Method open0 = in.getDeclaredMethod("open0",String.class);
//            open0.setAccessible(true);
//            open0.invoke(inputStream,"D:/abc.txt");
//        }
//        while ((len = inputStream.read(bytes)) != -1)
//            outputStream.write(bytes,0,len);
        inputStream.close(); // 释放对象所占用的系统资源，比如文件句柄
        outputStream.close();
        while(true); // 如果不关闭流，操作系统资源就会一直被占用，其他进程将无法访问该资源
    }
}
