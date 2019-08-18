import java.util.Scanner;

/**
 * @description: 汉诺塔问题
 * 古代有一个梵塔，塔内有三个座A、B、C，A座上有64个盘子，盘子大小不等，大的在下，小的在上。
 * 有一个和尚想把这64个盘子从A座移到C座，但每次只能允许移动一个盘子，并且在移动过程中，3个座上的盘子始终保持大盘在下，小盘在上。在移动过程中可以利用B座。
 * 当n=1时，将A上的盘子直接移动到C上
 * 当n>=2时，
 *      1. 将A上n-1个盘子移动到B上（此步骤的解决方法与移动n阶盘子的方法一样，只是问题的规模缩小1阶）
 *      2. 将A上的一个盘子移动到C
 *      3. 将B上的n-1个盘子移动到C上
 * @author: Daniel
 * @create: 2019-03-06-22-21
 **/
public class Tower {

    // 参数顺序表示有n个盘子，借助two,将one移动到three
    /*** 思考，为什么这样写会出现StackOverflowError 因为没有递归结束条件，永远不会返回，直到虚拟机栈所允许的最大深度，就会抛出StackOverflowError
     if(n == 1)
        move(one,three,1);
     tower(n - 1, one, three, two); // 将one移动到two
     move(one, three, n); // 此时的A上盘子编号是n
     tower(n - 1, two, one, three); // 将two移动到three
     */
    public static void tower(int n, char one, char two, char three) {
        if(n == 1) {
            move(one, three, 1);
            return;
        }
        tower(n - 1, one, three, two); // 将one移动到two
        move(one, three, n); // 此时的A上盘子编号是n
        tower(n - 1, two, one, three); // 将two移动到three
    }

    public static void move(char x, char y, int n) {
        System.out.println(x + "的第" + n + "盘移动到" + y);
    }

    public static void main(String[] args) {
        int level;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入层数：");
        level = scanner.nextInt();
        tower(level,'A','B','C');
    }
}
