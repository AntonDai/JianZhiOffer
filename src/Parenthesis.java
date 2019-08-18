import java.util.ArrayList;
import java.util.List;

/**
 * @description: 生成有效括号组合
 * @author: Daniel
 * @create: 2019-04-03-16-21
 **/
public class Parenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateOneByOne("", result , n, n);
        return result;
    }

    public static void generateOneByOne(String sublist, List<String> result, int left, int right) { // left、right表示还有多少个左括号、右括号可以使用
        if (left == 0 && right == 0) {// 都使用完了，表示组装完毕，可以添加进结果集
            result.add(sublist);
            return;
        }
        // 剪枝
        if (left > 0) // 左括号还没有使用完，先使用左括号，先使用右括号肯定是非法的
            generateOneByOne(sublist + "(", result , left - 1, right);
        if (right > left) // 右括号还没有使用完，并且右括号大于左括号，该使用右括号了
            generateOneByOne(sublist + ")", result, left, right - 1);
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
