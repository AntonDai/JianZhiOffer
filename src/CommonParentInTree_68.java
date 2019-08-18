import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description: 树中两个节点的最低公共祖先
 * @author: Daniel
 * @create: 2019-03-01-09-03
 **/
public class CommonParentInTree_68 {
    // 用来得到从根节点root开始到达节点node的路径
    public boolean getNodePath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {
        if(root == node)
            return true;
        path.add(root);
        boolean found = false;
        if(!found && root != null) {
            found = getNodePath(root.left,node,path) || getNodePath(root.right,node,path);
        }
        if(!found)
            path.remove(path.size() - 1);
        return found;
    }
    // 用来得到两条路径的最后一个公共节点
    public TreeNode getLastCommonNode(ArrayList<TreeNode> path1, ArrayList<TreeNode> path2) {
        TreeNode lastCommonNode = null;
        Iterator<TreeNode> iterator1 = path1.iterator();
        Iterator<TreeNode> iterator2 = path2.iterator();
        while(iterator1.hasNext() && iterator2.hasNext()) {
            if(iterator1.next() == iterator2.next())
                lastCommonNode = iterator1.next();
        }
        return lastCommonNode;
    }

    public TreeNode getLastCommonParent(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root == null || node1 == null || node2 == null)
            return null;
        ArrayList<TreeNode> path1 = new ArrayList<>();
        ArrayList<TreeNode> path2 = new ArrayList<>();
        getNodePath(root,node1,path1);
        getNodePath(root,node2,path2);
        return getLastCommonNode(path1,path2);
    }

    //     1
    //    2  3
    //  4   5
    // 6 7 8  9
    public void test1() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        connectNode(node1,node2,node3);
        connectNode(node2,node4,node5);
        connectNode(node4,node6,node7);
        connectNode(node5,node8,node9);

        TreeNode lastCommonParent = getLastCommonParent(node1, node6, node8);
        System.out.println(lastCommonParent.val == 2);
    }

    //     1
    //    2
    //   3
    //  4
    // 5
    //6 7
    public void test2() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        connectNode(node1,node2,null);
        connectNode(node2,node3,null);
        connectNode(node3,node4,null);
        connectNode(node4,node5,null);
        connectNode(node5,node6,node7);

        TreeNode lastCommonParent = getLastCommonParent(node1, node3, node4); // 最低公共祖先，3和4的最低公共祖先是2
        System.out.println(lastCommonParent.val == 2);
    }

    //    1
    //   2
    //  3
    // 4
    public void test3() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        connectNode(node1,node2,null);
        connectNode(node2,node3,null);
        connectNode(node3,node4,null);

        TreeNode lastCommonParent = getLastCommonParent(node1, node2, node5); // 一个节点不在树中
        System.out.println(lastCommonParent == null);
    }

    public void connectNode(TreeNode node, TreeNode node1, TreeNode node2) {
        if(node == null) return;
        node.left = node1;
        node.right = node2;
    }

    public static void main(String[] args) {
        CommonParentInTree_68 commonParentInTree68 = new CommonParentInTree_68();
        commonParentInTree68.test1();
        commonParentInTree68.test2();
        commonParentInTree68.test3();
    }
}
