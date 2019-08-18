package util;

import java.io.File;
import java.io.IOException;

/**
 * @description: File对象演示demo
 * @author: Daniel
 * @create: 2019-03-27-17-51
 **/
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/DataStructure/src/BinarySearchTree.java");
        System.out.println(file.exists()); // 是否存在
        System.out.println(file.isDirectory()); // 是否是目录
        System.out.println(file.getName()); // 获取文件名 BinarySearchTree.java
        System.out.println(file.getPath()); // 获取文件路径 D:\DataStructure\src\BinarySearchTree.java
        System.out.println(file.getAbsolutePath()); // D:\DataStructure\src\BinarySearchTree.java
        System.out.println(file.getCanonicalPath()); // D:\DataStructure\src\BinarySearchTree.java
        System.out.println(File.separator); // \
        System.out.println(File.separatorChar); // \
    }
}
