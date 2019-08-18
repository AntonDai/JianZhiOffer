/**
 * @description: 将有序数组转换为二叉搜索树
 * @author: Daniel
 * @create: 2019-03-10-22-18
 **/
public class ConvertSortedArrayToBinaryTree {
    // 以有序数组的中间值作为根节点，然后不断划分子数组，递归
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
            return null;
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int low, int high) {
        if(high < low)
            return null;
        int mid = low + ((high - low) >> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums,low,mid-1);
        node.right = sortedArrayToBST(nums,mid+1,high);
        return node;
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBinaryTree convertSortedArrayToBinaryTree = new ConvertSortedArrayToBinaryTree();
        convertSortedArrayToBinaryTree.test1();
    }

    public void test1() {
        int[] nums = {1,2,3,4,5,6,7,8};
        TreeNode root = sortedArrayToBST(nums);
        print(root);
    }

    // Morris中序遍历算法的步骤如下：
    // 如果当前节点的左孩子为空，则打印当前节点并将其右孩子作为当前节点。
    // 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
    // 如果前驱节点的右孩子为空，那么将前驱节点的右孩子指向当前节点，然后将当前节点更新为当前节点的左孩子。
    // 如果前驱节点的右孩子为当前节点（即前驱节点的右孩子指向了当前节点），那么将前驱节点的右孩子重新设为空（恢复树的形状），打印当前节点，然后将当前节点更新为当前节点的右孩子。
    // 重复步骤1、2直到当前节点为空。
    public void print(TreeNode node) {
        TreeNode cur = node, pre = null;
        while(cur != null) {
            if(cur.left == null) {
                System.out.print(cur.val + " ");
                cur = cur.right;
            }else {
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    pre.right = null;
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }
}
