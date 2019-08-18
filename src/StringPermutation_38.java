import java.util.ArrayList;
import java.util.Collections;

/**
 * @description: 字符串的排列
 * 回溯法
 * @author: Daniel
 * @create: 2019-02-20-20-15
 **/
public class StringPermutation_38 {
    public ArrayList<String> permutation(char[] str) {
        ArrayList<String> list = new ArrayList<>();
        if(str != null && str.length > 0) {
            permutation(str, 0, list);
            // 若要求输出排序，则可以加上下面的代码
            Collections.sort(list);
        }
        return list;
    }

    public void permutation(char[] str, int i, ArrayList<String> list) {
        if(i == str.length - 1) {
            String s = new String(str);
            if(!list.contains(s)) // 去重
                list.add(s);
        } else {
            for(int j=i; j<str.length; j++) {
                swap(str,i,j);
                permutation(str,i+1,list);
                swap(str,i,j); // 两次交换，回到上一个状态
            }
        }
    }

    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        StringPermutation_38 stringPermutation38 = new StringPermutation_38();
        char[] str = {'a','b','c'};
        ArrayList<String> list = stringPermutation38.permutation(str);
        System.out.println(list);
        char[] str2 = {'a','a','c'};
        System.out.println(stringPermutation38.permutation(str2));
    }
}
