/**
 * @description: 把数字翻译成字符串
 * @author: Daniel
 * @create: 2019-02-22-15-41
 **/
public class TranslateNumbersToStrings_46 {
    public int getTranslationCount(int number) {
        if(number < 0)
            return 0;
        String numString = Integer.toString(number);
        return getTranslationCount(numString);
    }

    public int getTranslationCount(String num) {
        int length = num.length();
        int[] counts = new int[length];
        int count = 0;
        for(int i=length-1; i>=0; i--) {
//            count = 0;
            if(i < length - 1)
                count = counts[i+1];
            else
                count = 1;
            if(i < length - 1) {
                int digit1 = num.charAt(i) - '0';
                int digit2 = num.charAt(i+1) - '0';
                int converted = digit1 * 10 + digit2;
                if(converted >= 10 && converted <= 25) {
                    if(i < length - 2)
                        count += counts[i+2];
                    else
                        count++;
                }
            }
            counts[i] = count;
        }
        return counts[0];
    }

    public int getTranslationCount2(int num) {
        if(num < 0)
            return 0;
        return getTranslationCount2(String.valueOf(num),String.valueOf(num).length() - 1);
    }

    public int getTranslationCount2(String num, int i) {
        if(i <= 0)
            return 1;
        int flag = 0;
        int m = num.charAt(i-1) - '0';
        int n = num.charAt(i) - '0';
        int converted = m * 10 + n;
        if(converted >= 10 && converted <= 25)
           flag = 1;
        return getTranslationCount2(num,i-1) + getTranslationCount2(num,i-2) * flag;
    }

    public static void main(String[] args) {
        TranslateNumbersToStrings_46 translateNumbersToStrings46 = new TranslateNumbersToStrings_46();
        System.out.println("12258: " + translateNumbersToStrings46.getTranslationCount2(12258)); // 5
        System.out.println("3: " + translateNumbersToStrings46.getTranslationCount2(3)); // 1
        System.out.println("26: " + translateNumbersToStrings46.getTranslationCount2(26)); // 1
        System.out.println("4325523: " + translateNumbersToStrings46.getTranslationCount2(4325523)); // 4
    }

}
