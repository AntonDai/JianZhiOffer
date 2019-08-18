/**
 * @description: 二叉树的深度
 * @author: Daniel
 * @create: 2019-02-23-19-20
 **/
public class TreeDepth_55 {
    public int depth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(depth(root.left),depth(root.right)) + 1;
    }
}
