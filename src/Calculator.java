import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @description: 实现一个计算器
 * @author: Daniel
 * @create: 2019-03-24-20-32
 **/
public class Calculator {
    /**
     * 表达式求值
     */
    public BigDecimal calculate(String resExp) {
        String[] exp = inExpToSuffixExp(resExp).split(",");
        // 定义一个存放操作数的栈
        LinkedList<String> stack = new LinkedList<>();
        for(int i = 0; i < exp.length; i++) {
            if(!isOperator(exp[i])) {
                // 如果是操作数，则压入操作数栈
                stack.push(exp[i]);
            } else {
                // 如果是操作符，弹出栈中靠近栈顶的两个操作数
                BigDecimal num2 = new BigDecimal(stack.pop());
                BigDecimal num1 = new BigDecimal(stack.pop());
                if("+".equals(exp[i])) {
                    // 遇到“+”，将两个操作数相加，然后将结果压栈
                    stack.push(num1.add(num2) + "");
                }else if("-".equals(exp[i])) {
                    // 遇到“-”，将两个操作数相减,然后将结果压栈
                    stack.push(num1.subtract(num2) + "");
                }else if("*".equals(exp[i])) {
                    // 遇到“*”，将两个操作数相乘,然后将结果压栈
                    stack.push(num1.multiply(num2) + "");
                }else if("/".equals(exp[i])) {
                    // 遇到“/”，将两个操作数相除,然后将结果压栈
                    stack.push(num1.divide(num2) + "");
                }
            }
        }
        // 将最后的结果弹出并返回
        return new BigDecimal(stack.pop());
    }

    /**
     * 将中缀表达式转换为后缀表达式
     */
    public String inExpToSuffixExp(String resExp) {
        String[] inExp = dealInExp(resExp).split(",");
        LinkedList<String> stack = new LinkedList<>(); // 用来存放运算符
        StringBuilder sb = new StringBuilder();
        for(String s : inExp) {
            if (!isOperator(s)) {
                // 遇到数字，直接输出
                sb.append(s);
                sb.append(",");
            }else if("(".equals(s)){
                // 遇到左括号直接压栈
                stack.push(s);
            }else if(")".equals(s)) {
                // 遇到右括号，将栈顶元素依次弹出，直到遇到左括号
                while(!"(".equals(stack.peek())) {
                    sb.append(stack.pop());
                    sb.append(",");
                }
                // 弹出左括号但不输出
                stack.pop();
            }else if(isOperator(s)) {
                // 遇到运算符，当前运算符优先级小于等于栈顶运算符优先级，将栈顶运算符弹出
                // 当前运算符继续和新的栈顶运算符比较...
                while(!stack.isEmpty() && getPriority(s) <= getPriority(stack.peek())) {
                    sb.append(stack.pop());
                    sb.append(",");
                }
                stack.push(s);
            }
        }
        // 将栈内还没有弹出的运算符依次弹出
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(",");
        }
        // 去除最后一个“,”
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    /**
     * 预处理中缀表达式
     */
    private String dealInExp(String resExp) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < resExp.length(); i++) {
            String tmp = resExp.charAt(i) + "";
            if(!isOperator(tmp)) { // 操作数
                sb.append(tmp);
            }else {
                if(sb.length() == 0)
                    sb.append(tmp);
                else if(sb.charAt(sb.length() - 1) == ',')
                    sb.append(tmp);
                else {
                    sb.append(",");
                    sb.append(tmp);
                }
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 判断是不是运算符或括号
     */
    private boolean isOperator(String str) {
        return "+-*/()".contains(str);
    }

    /**
     * 获得运算符的优先级
     */
    private int getPriority(String operator) {
        // () +- */ 优先级
        return "()+-*/".indexOf(operator) / 2;
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("请输入表达式：");
//        String resExp = in.nextLine();
        Calculator calculator = new Calculator();
//        BigDecimal result = calculator.calculate(resExp);
//        System.out.println(resExp + " = " + result);
//        in.close();
        System.out.println(calculator.calculate("12+63*(10-6*23)"));
    }
}
