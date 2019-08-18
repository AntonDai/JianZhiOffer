/**
 * @description: 替换空格
 * @author: Daniel
 * @create: 2019-02-15 10:55
 **/
public class ReplaceSpaces {
    public static void main(String[] args){
        String str1 = "We are happy.";
        String str2 = "  We are happy.  ";
        String str3 = "Wearehappy.";
        String str4 = "";
        String str5 = " ";
        ReplaceSpaces replaceSpaces = new ReplaceSpaces();
        System.out.println(replaceSpaces.replaceSpace(str1));
        System.out.println(replaceSpaces.replaceSpace(str2));
        System.out.println(replaceSpaces.replaceSpace(str3));
        System.out.println(replaceSpaces.replaceSpace(str4));
        System.out.println(replaceSpaces.replaceSpace(str5));

        System.out.println("-----------------------------");

        System.out.println(replaceSpaces.replaceSpace2(str1));
        System.out.println(replaceSpaces.replaceSpace2(str2));
        System.out.println(replaceSpaces.replaceSpace2(str3));
        System.out.println(replaceSpaces.replaceSpace2(str4));
        System.out.println(replaceSpaces.replaceSpace2(str5));
    }

    public String replaceSpace(String str) {
        if(str == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c == ' ')
                sb.append("%20");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    public String replaceSpace2(String str) {
        if(str == null) return null;
        char[] chars = str.toCharArray();
        int numberOfBlank = 0;
        for(char c : chars)
            if(c == ' ')
                numberOfBlank++;
        int newLength = str.length() + numberOfBlank * 2;
        char[] newChars = new char[newLength];
        int p1 = str.length() - 1;
        int p2 = newLength - 1;
        while(p1 >= 0) {
            if(chars[p1] == ' ') {
                newChars[p2--] = '0';
                newChars[p2--] = '2';
                newChars[p2--] = '%';
            } else{
                newChars[p2--] = chars[p1];
            }
            p1--;
        }
        return new String(newChars);
    }
}
