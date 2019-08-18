import java.util.LinkedList;

/**
 * @description: 二叉树的遍历与重建
 * @author: Daniel
 * @create: 2018-12-27 17:18
 **/
public class BinaryTree<T extends Comparable<T>> {
    private class TreeNode {
        T val;
        TreeNode left;
        TreeNode right;

        TreeNode(T x) { val = x; }
    }

    public void preOrderTraverse1(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrderTraverse1(root.left);
        preOrderTraverse1(root.right);

    }

    public void preOrderTraverse2(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            // 先压右孩子，再压左孩子，这样先访问的就是左孩子
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.left);
            System.out.print(root.val + " ");
            inOrderTraverse1(root.right);
        }
    }

    public void inOrderTraverse2(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) { // 一直遍历到左子树最下边
                stack.push(pNode);
                pNode = pNode.left;
            } else { // pNode == null && !stack.isEmpty() 此时已经到达左子树最下边，这时需要出栈访问了
                pNode = stack.pop();
                System.out.print(pNode.val + " ");
                pNode = pNode.right;
            }
        }
    }

    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            postOrderTraverse1(root.left);
            postOrderTraverse1(root.right);
            System.out.print(root.val+" ");
        }
    }

    public void postOrderTraverse2(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null; // 当前节点的前一次访问的节点
        TreeNode cur = root; // 当前节点
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode node = stack.peek();
                if(node.right != null && node.right != pre)
                    cur = node.right;
                else{
                    System.out.print(node.val + " ");
                    pre = stack.pop();
                }
            }
        }
    }

    // 双栈法
    public void postOrderTraverse3(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while(!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root); // 按照根节点->右子树->左子树的顺序压入stack2中
            if(root.left != null)
                stack1.push(root.left);
            if(root.right != null)
                stack1.push(root.right);
        }
        while(!stack2.isEmpty()) // 按照左子树->右子树—>根节点的顺序从stack2中弹出
            System.out.print(stack2.pop().val + " ");
    }

    public void levelTraverse(TreeNode root) {
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

    public TreeNode reConstructBinaryTree(T[] pre,T[] in) { // 根据前序和中序遍历序列重建二叉树
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    private TreeNode reConstructBinaryTree(T[] pre, int startPre, int endPre, T[]in, int startIn, int endIn) {
        if(startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]); // 确定根节点
        for(int i = startIn; i <= endIn; i++)
            if(in[i] == pre[startPre]) { // 根据根节点划分左子树和右子树
                root.left = reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1); // 在左子树中递归
                root.right = reConstructBinaryTree(pre,startPre+1+i-startIn,endPre,in,i+1,endIn); // 在右子树中递归
            }
        return root;
    }

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
//        Integer[] pre = {1,2,4,7,3,5,6,8};
//        Integer[] in = {4,7,2,1,5,3,8,6};
        String[] pre = {"A","B","D","H","I","E","J","C","F","K","G"};
        String[] in = {"H","D","I","B","E","J","A","F","K","C","G"};
        BinaryTree.TreeNode root = tree.reConstructBinaryTree(pre, in);
        System.out.println("=========前序遍历=========");
        tree.preOrderTraverse1(root);
        System.out.println();
        tree.preOrderTraverse2(root);
        System.out.println();
        System.out.println("=========中序遍历=========");
        tree.inOrderTraverse1(root);
        System.out.println();
        tree.inOrderTraverse2(root);
        System.out.println();
        System.out.println("=========后序遍历=========");
        tree.postOrderTraverse1(root);
        System.out.println();
        tree.postOrderTraverse2(root);
        System.out.println();
        tree.postOrderTraverse3(root);
        System.out.println();
        System.out.println("=========层次遍历=========");
        tree.levelTraverse(root);
        System.out.println();
    }
}
