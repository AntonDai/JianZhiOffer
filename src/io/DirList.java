package io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @description: 查看一个目录列表
 * @author: Daniel
 * @create: 2019-04-15-15-56
 **/
public class DirList {
    private static String path;

    public static void getProperties() throws IOException {
        InputStream inputStream = DirList.class.getResourceAsStream("./resource/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        path = properties.getProperty("path");
        System.out.println("path : " + path);
    }
    public static void main(String[] args) {
        try {
            getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        String[] list;
        if(args.length == 0)
            list = file.list();
        else
            list = file.list(new DirFilter(args[0])); // 策略模式，回调
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String item : list)
            System.out.println(item);
        System.out.println(Arrays.toString(args));
    }
    // 目录过滤器
    static class DirFilter implements FilenameFilter {
        private Pattern pattern;

        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }
        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }
}
