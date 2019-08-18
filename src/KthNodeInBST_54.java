import java.util.LinkedList;

/**
 * @description: 二叉搜索书的第K大节点 (K > 0)
 * @author: Daniel
 * @create: 2019-02-23-19-03
 **/
public class KthNodeInBST_54 {
    public TreeNode kthNode(TreeNode root, int k) {
        if(root == null || k == 0)
            return null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || root != null) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                if(--k == 0) // 这里可以想一下，如果k为0呢，所以 前面要判断k是否等于0
                    return root;
                root = root.right;
            }
        }
        return null;
    }
}
