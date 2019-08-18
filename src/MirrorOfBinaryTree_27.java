import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 二叉树的镜像
 * @author: Daniel
 * @create: 2019-03-05-08-27
 **/
public class MirrorOfBinaryTree_27 {
    public void mirrorRecursive(TreeNode root) {
        if(root == null)
            return;
        if(root.left == null && root.right == null)
            return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if(root.left != null)
            mirrorRecursive(root.left);
        if(root.right != null)
            mirrorRecursive(root.right);
    }

    public void mirrorIterative(TreeNode root) {
        if(root == null)
            return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            root = stack.pop();
            if(root.left != null || root.right != null) {
                TreeNode tmp = root.left;
                root.left = root.right;
                root.right = tmp;
            }
            if(root.right != null)
                stack.push(root.right);
            if(root.left != null)
                stack.push(root.left);
        }
    }

    public static void main(String[] args) {
        MirrorOfBinaryTree_27  mirrorOfBinaryTree_27 = new MirrorOfBinaryTree_27();
        mirrorOfBinaryTree_27.test1();
    }

    //       8               8
    //     6   10          10   6
    //    5 7 9  11      11  9 7 5
    public void test1() {
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node10 = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node11 = new TreeNode(11);

        connect(node8,node6,node10);
        connect(node6,node5,node7);
        connect(node10,node9,node11);

        mirrorRecursive(node8);
        levelTraverse(node8);
        mirrorIterative(node8);
        levelTraverse(node8);
    }

    public void connect(TreeNode node, TreeNode node1, TreeNode node2) {
        if(node == null)
            return;
        node.left = node1;
        node.right = node2;
    }

    public void levelTraverse(TreeNode node) {
        if(node == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.val + " ");
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        System.out.println();
    }
}
