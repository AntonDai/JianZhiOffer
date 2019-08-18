/**
 * @description: 翻转单词顺序
 * @author: Daniel
 * @create: 2019-02-25-08-29
 **/
public class ReverseWordsInSentence_58 {
    public String reverseSentence(String str) {
        if(str == null || str.length() == 0)
            return str;
        char[] chars = str.toCharArray();
        reverse(chars,0,chars.length - 1);
        int nBlank = -1; // 记录空格的起始位置
        for(int i=0; i<chars.length; i++) { // 要注意最后一个单词通常后面没有空格，我们要单独翻转
            if(chars[i] == ' ') {
                reverse(chars,nBlank + 1,i-1);
                nBlank = i; // 更新空格的位置
            }
        }
        // 翻转最后一个单词
        reverse(chars,nBlank+1,chars.length - 1);
        return new String(chars);
    }

    public String leftRotate(String str, int k) {
        if(str == null || k < 0 || k > str.length() - 1)
            return str;
        char[] chars = str.toCharArray();
        reverse(chars,0,chars.length - 1);
        reverse(chars,0,chars.length - k - 1);
        reverse(chars,chars.length - k, chars.length - 1);
        return new String(chars);
    }

    public void reverse(char[] str, int low, int high) {
        while(low < high) {
            char temp = str[low];
            str[low] = str[high];
            str[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        ReverseWordsInSentence_58 reverseWordsInSentence58 = new ReverseWordsInSentence_58();
        String str = "I am a student.";
        System.out.println(reverseWordsInSentence58.reverseSentence(str));
        System.out.println(reverseWordsInSentence58.leftRotate("abcdefg",2));
    }
}
