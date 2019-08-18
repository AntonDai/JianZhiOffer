import java.util.LinkedList;

/**
 * @description: 归并两棵树
 * 将两个树归并为一棵树，如果在某个节点上，两棵树都不为空，那么就将两个节点的值加起来，否则只取有值的那棵树的节点的值
 * @author: Daniel
 * @create: 2019-03-07-22-09
 **/
public class MergeBinaryTree {
    public static TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null)
            return null;
        if(node1 == null)
            return node2;
        if(node2 == null)
            return node1;
        TreeNode root = new TreeNode(node1.val + node2.val);
        root.left = mergeTrees(node1.left,node2.left);
        root.right = mergeTrees(node1.right, node2.right);
        return root;
    }

    public static void main(String[] args) {
        test1();
    }

    //    0              0
    //   1  2          1   2
    //               3
    //
    //      0
    //    2   4
    //   3
    public static void test1() {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        connect(node0,node1,node2);

        TreeNode node00 = new TreeNode(0);
        TreeNode node11 = new TreeNode(1);
        TreeNode node22 = new TreeNode(2);
        TreeNode node33 = new TreeNode(3);

        connect(node00,node11,node22);
        connect(node11,node33,null);

        TreeNode root = mergeTrees(node0, node00);
        levelTraverse(root);
    }

    public static void connect(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root != null) {
            root.left = node1;
            root.right = node2;
        }
    }

    public static void levelTraverse(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            System.out.print(pNode.val + " ");
            if(pNode.left != null)
                queue.offer(pNode.left);
            if(pNode.right != null)
                queue.offer(pNode.right);
        }
    }
}
