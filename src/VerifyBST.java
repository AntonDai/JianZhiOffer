import java.util.LinkedList;

/**
 * @description: 验证二叉搜索树
 * @author: Daniel
 * @create: 2019-03-05-09-08
 **/
public class VerifyBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, TreeNode left, TreeNode right) {
        if(root == null) // 遍历到叶子节点，返回true
            return true;
        if((left != null && left.val >= root.val) || (right != null && right.val <= root.val))
            return false;
        return isValidBST(root.left,left,root)  // 遍历左子树，左子树全部小于根节点和右子树 isValidBST(root.left,null,root)
                && isValidBST(root.right,root,right); // 遍历右子树，右子树全部大于根节点和左子树 isValidBST(root.right,root,null)
    }

    // 利用中序遍历的有序性，依次比较当前节点和前一个节点的大小即可
    public boolean isValidBSTIterative(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisitedNode = null;
        while(root != null || !stack.isEmpty()) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                if(lastVisitedNode != null && root.val <= lastVisitedNode.val)
                    return false;
                lastVisitedNode = root;
                root = root.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        VerifyBST verifyBST = new VerifyBST();
        verifyBST.test1();
        verifyBST.test2();
    }

    //      5
    //    3  7
    //   2  6
    public void test1() {
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);

        connect(node5,node3,node7);
        connect(node3,node2,null);
        connect(node7,node6,null);

        System.out.println(isValidBST(node5));
        System.out.println(isValidBSTIterative(node5));
    }

    //    5
    //   4 6
    //  7 3
    public void test2() {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);

        connect(node5,node4,node6);
        connect(node4,node7,node3);

        System.out.println(isValidBST(node5));
        System.out.println(isValidBSTIterative(node5));
    }

    public void connect(TreeNode node, TreeNode node1, TreeNode node2) {
        if(node == null)
            return;
        node.left = node1;
        node.right = node2;
    }
}
