import java.util.Queue;
import java.util.LinkedList;

/**
 * @description: 字符流中第一个只出现一次的字符
 * @author: Daniel
 * @create: 2019-02-23-10-53
 **/
public class FirstCharacterInStream_50_1 {
    int[] freq = new int[256];
    Queue<Character> queue = new LinkedList<Character>();

    int[] occurrence = new int[256];
    private static int index = 0;

    public FirstCharacterInStream_50_1() {
        init(occurrence);
    }

    /**思路一：使用一个队列，通过查询数组判断队首元素是否重复出现过，若有，则删除首元素，否则，返回队首元素*/
    public void insert(char ch) {
        freq[ch]++;
        if(freq[ch] == 1)
            queue.offer(ch);
    }

    public char firstAppear() {
        while(!queue.isEmpty() && freq[queue.peek()] >= 2)
            queue.poll();
        return queue.isEmpty() ? '\u0000' : queue.peek();
    }

    /**思路二：不使用队列*/
    public void init(int[] array) {
        for(int i=0; i<array.length; i++)
            array[i] = -1;
    }

    public void insert2(char ch) {
        if(occurrence[ch] == -1) // 第一次出现
            occurrence[ch] = index;
        else if(occurrence[ch] >= 0) // 重复出现
            occurrence[ch] = -2;
        index++;
    }

    public char firstAppear2() {
        int min = Integer.MAX_VALUE;
        char result = '\u0000';
        for(int i=0; i<occurrence.length; i++) {
            if(occurrence[i] >=0 && occurrence[i] < min) {
                result = (char)i;
                min = occurrence[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FirstCharacterInStream_50_1 firstCharacterInStream50_1 = new FirstCharacterInStream_50_1();
        String str = "google";

        for(int i=0; i<str.length(); i++) {
            firstCharacterInStream50_1.insert(str.charAt(i));
            System.out.println(firstCharacterInStream50_1.firstAppear());
        }

        System.out.println("----------------");

        for(int i=0; i<str.length(); i++) {
            firstCharacterInStream50_1.insert2(str.charAt(i));
            System.out.println(firstCharacterInStream50_1.firstAppear2());
        }
    }
}
