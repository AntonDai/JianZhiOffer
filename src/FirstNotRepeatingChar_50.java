import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 第一次只出现一次的字符
 * @author: Daniel
 * 注意，如果要使用hashmap，就要发挥出hashmap的查找优势，而不要去遍历hashmap
 * @create: 2019-02-23-10-17
 **/
public class FirstNotRepeatingChar_50 {

    // 返回第一个不重复字符的位置
    public static int firstUniqueChar(String str) {
        if(str == null || str.length() == 0)
            return -1;
        int[] frequency = new int[256]; // 用一个数组做一个简易的hashmap
        for(int i=0; i<str.length(); i++)
            frequency[str.charAt(i)]++;
        for(int i=0; i<str.length(); i++)
            if(frequency[str.charAt(i)] == 1)
                return i;
        return -1;
    }

    public static int firstUniqueChar2(String str) {
        if(str == null || str.length() == 0)
            return -1;
//        Map<Character,Integer> hashMap = new LinkedHashMap<>(); // 保证插入顺序
        Map<Character,Integer> hashMap = new HashMap<>();
        for(int i=0; i<str.length(); i++) {
            Integer count = hashMap.get(str.charAt(i));
            hashMap.put(str.charAt(i),count == null ? 1 : count + 1);
        }
        // 若使用LinkedHashMap也可以直接遍历hashmap，但这种方式并没有发挥出hashmap查找一个元素的优势O(1)
//        for(Map.Entry<Character,Integer> entry : hashMap.entrySet()) {
//            if(entry.getValue() == 1)
//                return str.indexOf(entry.getKey());
//        }
        // 若使用HashMap，由于是无序的，要得到第一个唯一的字符，只有遍历字符串
        for(int i=0; i<str.length(); i++) {
            if(hashMap.get(str.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "abaccdeff";
        System.out.println(firstUniqueChar(str));
        System.out.println(firstUniqueChar2(str));
    }
}
