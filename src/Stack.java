import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 用两个队列实现一个栈
 * 要点：任何时候两个队列中总有一个为空；
 * 添加元素总是向非空队列中添加；
 * 取出元素时总是将元素除队尾最后一个元素外，导入另一个空队列，再把最后一个元素出队。
 * @author: Daniel
 * @create: 2019-02-16-16-19
 **/
public class Stack {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        test(stack.pop(),3);
        test(stack.pop(),2);
        stack.push(4);
        test(stack.pop(),4);
        test(stack.pop(),1);
        stack.pop(); // 栈为空，抛异常
    }

    public void push(int node) {
        // 选一个非空的队列入队
        if(!queue1.isEmpty())
            queue1.offer(node);
        else if(!queue2.isEmpty())
            queue2.offer(node);
        else // 两个队列都为空，任选一个
            queue1.offer(node);
    }

    public int pop() {
        if(queue1.isEmpty() && queue2.isEmpty())
            throw new RuntimeException("Stack is empty!");
        if(!queue1.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }else {
            while(queue2.size() > 1) {
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
    }

    public static void test(int actual, int expected) {
        if(actual == expected)
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }
}
