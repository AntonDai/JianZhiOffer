/**
 * @description: 树的子结构
 * @author: Daniel
 * @create: 2019-03-05-07-57
 **/
public class SubstructureInTree_26 {
    public boolean hasSubTree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if(root1 != null && root2 != null) {
            // 先在树1中寻找与树2根节点相同的节点
            if(root1.val == root2.val)
                result = doesTree1HasTree2(root1,root2);
            if(!result)
                result = hasSubTree(root1.left,root2);
            if(!result)
                result = hasSubTree(root1.right,root2);
        }
        return result;
    }

    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if(root2 == null)
            return true;
        if(root1 == null)
            return true;
        if(root1.val != root2.val)
            return false;
        return doesTree1HasTree2(root1.left,root2.left) && doesTree1HasTree2(root1.right,root2.right);
    }

    public static void main(String[] args) {
        SubstructureInTree_26 substructureInTree_26 = new SubstructureInTree_26();
        substructureInTree_26.test1();
    }

    //         5                   3
    //      3      1                 6
    //    4  6    7 8                  9
    //        9  2
    public void test1() {
        // 树1
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        connect(node5,node3,node1);
        connect(node3,node4,node6);
        connect(node1,node7,node8);
        connect(node6,null,node9);
        connect(node7,node2,null);

        // 树2
        TreeNode node33 = new TreeNode(3);
        TreeNode node66 = new TreeNode(6);
        TreeNode node99 = new TreeNode(9);

        connect(node33,null,node66);
        connect(node66,null,node99);

        System.out.println(hasSubTree(node5,node33));
    }

    public void connect(TreeNode node, TreeNode node1, TreeNode node2) {
        if(node == null)
            return;
        node.left = node1;
        node.right = node2;
    }
}
