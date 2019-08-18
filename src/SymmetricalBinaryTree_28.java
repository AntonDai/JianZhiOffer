/**
 * @description: 对称的二叉树
 * 思路：前序，中序，后序遍历都是先遍历左子树，再遍历右子树，以前序遍历为例，我们可以定义一种对称的前序遍历，先遍历右子树，再遍历左子树，但注意节点全部一样的树，我们要把null指针也考虑进来
 * @author: Daniel
 * @create: 2019-03-05-08-52
 **/
public class SymmetricalBinaryTree_28 {

    public boolean isSymmetrical(TreeNode root) {
        return isSymmetrical(root,root);
    }

    public boolean isSymmetrical(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) // 考虑null,必须两个都是null,才能返回true,否则返回false
            return true;
        if(node1 == null || node2 == null)
            return false;
        if(node1.val != node2.val) // 比较节点是否相等
            return false;
        return isSymmetrical(node1.left,node2.right) && isSymmetrical(node1.right,node2.left);  // 对于树1来说是前序遍历，根左右，对于树2来说，是对称的前序遍历，根右左
    }
}
