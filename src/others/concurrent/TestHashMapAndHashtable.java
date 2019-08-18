package others.concurrent;

import java.util.*;

/**
 * @description: Hashmap 和 Hashtable 的区别
 * @author: Daniel
 * @create: 2019-02-22-21-38
 **/
public class TestHashMapAndHashtable {
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(null,null);
        hashMap.put("1","a");
        hashMap.put("2","c");
        hashMap.put("3","d");
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        Hashtable<String,String> hashtable = new Hashtable<>();
//        hashtable.put(null,null); // hashtable 键值都不能为空
        hashtable.put("1","a");
        hashtable.put("2","b");
        hashtable.put("3","c");

        Enumeration<String> elements = hashtable.elements();
        while(elements.hasMoreElements())
            System.out.print(elements.nextElement() + " ");
        System.out.println();

        Set<Map.Entry<String, String>> entrySet = hashtable.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while(it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
    }
}
