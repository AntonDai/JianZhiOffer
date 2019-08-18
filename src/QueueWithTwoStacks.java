import java.util.LinkedList;

/**
 * @description: 用两个栈实现队列
 * 思路：栈1用作入队列，栈2用作出队列，当栈2为空时，将栈1中元素全部压入栈2，栈2再弹栈
 * @author: Daniel
 * @create: 2019-02-16-15-58
 **/
public class QueueWithTwoStacks {

    LinkedList<Integer> stack1 = new LinkedList<>();
    LinkedList<Integer> stack2 = new LinkedList<>();

    public static void main(String[] args) {
        QueueWithTwoStacks queueWithTwoStacks = new QueueWithTwoStacks();
        queueWithTwoStacks.offer(1);
        queueWithTwoStacks.offer(2);
        queueWithTwoStacks.offer(3);

        test(queueWithTwoStacks.poll(),1);

        queueWithTwoStacks.offer(4);
        test(queueWithTwoStacks.poll(),2);
        test(queueWithTwoStacks.poll(),3);
        test(queueWithTwoStacks.poll(),4);
        test(queueWithTwoStacks.poll(),0); // 队列为空，抛异常
    }

    public void offer(int node) {
        stack1.push(node);
    }

    public int poll() {
        if(stack1.isEmpty() && stack2.isEmpty())
            throw new RuntimeException("QueueWithTwoStacks is empty!");
        if(stack2.isEmpty())
            while(!stack1.isEmpty())
                stack2.push(stack1.pop());
        return stack2.pop();
    }

    public static void test(int actual, int expected) {
        if(actual == expected)
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }
}
