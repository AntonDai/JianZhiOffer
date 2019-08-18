/**
 * @description: 验证平衡二叉树
 * @author: Daniel
 * @create: 2019-02-23-19-40
 **/
public class BalancedBinaryTree_55_1 {
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        return getDepth(root) != -1;
    }

    // 不用重复遍历一个节点多次，一边遍历，一边判断每个节点是否是平衡的
    public int getDepth(TreeNode root) {
        if(root == null)
            return 0;
        int left = getDepth(root.left);
        if(left == -1) return -1; // 遍历到该节点之前，已经遍历了它的左右子树
        int right = getDepth(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left,right);
    }

    public static void main(String[] args) {
        BalancedBinaryTree_55_1 balancedBinaryTree55_1 = new BalancedBinaryTree_55_1();
        balancedBinaryTree55_1.test1();
    }

    //      1
    //    2   3
    //  4   5
    //    6
    //      7
    public void test1() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.right = node6;
        node6.right = node7;

        System.out.println(isBalanced(node1));
    }
}
