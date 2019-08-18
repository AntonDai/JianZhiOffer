import java.util.ArrayList;

/**
 * @description: 二叉树中和为某一值的路径
 * @author: Daniel
 * @create: 2019-03-08-09-19
 **/
public class PathInTree_34 {

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        dfs(root,target,list,result);
        return result;
    }

    private static void dfs(TreeNode root, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result) {
        if(root == null)
            return;
        list.add(root.val);
        target -= root.val;
        if(root.left == null && root.right == null && target == 0)
            result.add(new ArrayList<>(list));
        dfs(root.left,target,list,result);
        dfs(root.right,target,list,result);
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        test1();
    }

    //      10
    //    5    12
    //   4  7
    public static void test1() {
        TreeNode node10 = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node12 = new TreeNode(12);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);

        connect(node10,node5,node12);
        connect(node5,node4,node7);

        System.out.println(findPath(node10,22));
    }

    public static void connect(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root != null) {
            root.left = node1;
            root.right = node2;
        }
    }
}
