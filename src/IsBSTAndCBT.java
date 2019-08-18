import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 判断一棵二叉树是否是搜索二叉树、完全二叉树（Complete Binary Tree）
 * @author: Daniel
 * @create: 2019-03-29-14-50
 **/
public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    // 验证二叉搜索树
    // Morris遍历
    // 左子树小、右子树大
    public static boolean isBST(Node head) {
        if(head == null)
            return true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null) {
            // 寻找前驱节点
            cur2 = cur1.left;
            if(cur2 != null) {
                while(cur2.right != null && cur2.right != cur1)
                    cur2 = cur2.right;
                if(cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else {
                    cur2.right = null; // 恢复树
                }
            }
            if(pre != null && pre.value > cur1.value) // 如果前驱节点大于当前节点直接返回false
                return false;
            pre = cur1;
            cur1 = cur1.right;
        }
        return true;
    }
    // 验证完全二叉树
    public static boolean isCBT(Node head) {
        if(head == null)
            return true;
        Queue<Node> queue = new LinkedList<>();
        boolean leaf = false;
        queue.offer(head);
        while(!queue.isEmpty()) {
            head = queue.poll();
            // 1. 如果当前节点有右节点没有左节点，直接返回false
            // 2. 不违反1的情况下，如果当前节点左右不全，剩下的节点必须全都是叶子节点
            //        4
            //      2   5
            //    1   3
            //   0
            if((leaf && (head.left != null || head.right != null)) || (head.left == null && head.right != null))
                return false;
            if(head.left != null)
                queue.offer(head.left);
            if(head.right != null)
                queue.offer(head.right);
            else // 如果右子树为空，左子树不为空，接下来的节点都必须是叶子节点
                leaf = true;
        }
        return true;
    }

    /*********测试用代码***********/
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head,0,"H",17);
        System.out.println();
    }

    public static void printInOrder(Node head, int hight, String to, int len) {
        if(head == null) {
            return;
        }
        printInOrder(head.right,hight + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(hight * len) + val);
        printInOrder(head.left, hight + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; i++)
            sb.append(space);
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
