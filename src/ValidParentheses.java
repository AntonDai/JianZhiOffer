import java.util.LinkedList;

/**
 * @description: 有效的括号 {{{{}([][]){}}}}
 * @author: Daniel
 * @create: 2019-03-10-22-45
 **/
public class ValidParentheses {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if(c == '(')
                stack.push(')');
            else if(c == '[')
                stack.push(']');
            else if(c == '{')
                stack.push('}');
            else if(stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
     }
}
