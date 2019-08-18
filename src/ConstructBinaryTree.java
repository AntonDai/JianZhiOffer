import java.util.LinkedList;

/**
 * @description: 重建二叉树
 * 问题：如果输入的前序和中序（或中序和后序序列）不匹配呢？
 * @author: Daniel
 * @create: 2019-02-15-20-06
 **/
public class ConstructBinaryTree {
    public static void main(String[] args) {
        // 不完全二叉树
        int[] pre1 = {1,2,4,7,3,5,6,8};
        int[] in1  = {4,7,2,1,5,3,8,6};
        int[] post1 = {7,4,2,5,8,6,3,1};
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
        TreeNode root = constructBinaryTree.reConstructBinaryTree(pre1, in1);
        // 根据前序和中序重建后的二叉树打印后序序列
        System.out.println("根据前序和中序重建后的二叉树打印后序序列：");
        constructBinaryTree.postTraverse(root); // 7 4 2 5 8 6 3 1

        // 斜树，以只有左子树为例
        int[] pre2 = {1,2,3,4};
        int[] in2 = {4,3,2,1};
        int[] post2 = {4,3,2,1};
        TreeNode root2 = constructBinaryTree.reConstructBinaryTree2(pre2,in2);
        System.out.println("对于斜树的重建后的后序遍历：");
        constructBinaryTree.postTraverse(root2);

        // 只有一个根节点的树
        int[] pre3 = {1};
        int[] in3 = {1};
        int[] post3 = {1};
        TreeNode root3 = constructBinaryTree.reConstructBinaryTree(pre3,in3);
        System.out.println("对于只有一个根节点的树，重建后的后序遍历：");
        constructBinaryTree.postTraverse(root3);

        // 序列不匹配的测试
        int[] pre4 = {1,2,3};
        int[] in4 = {2,1,3}; // 匹配
        int[] in4_1 = {3,1,2}; // 不匹配
        int[] in4_2 = {1,3,2}; // 匹配
        int[] post4 = {3,2,1}; // 这是错误的后序
        System.out.println("序列不匹配的情况");
        TreeNode root4 = constructBinaryTree.reConstructBinaryTree(pre4,in4);
//        TreeNode root4_1 = constructBinaryTree.reConstructBinaryTree(pre4,in4_1); // 不匹配，无效输入
        TreeNode root4_2 = constructBinaryTree.reConstructBinaryTree(pre4,in4_2);
        constructBinaryTree.postTraverse(root4); // 匹配，后序{2,3,1}
//        constructBinaryTree.postTraverse(root4_1);
        constructBinaryTree.postTraverse(root4_2); // 匹配，后序{3,2,1}

        /** 下面测试根据中序和后序重建二叉树*/

        // 对于不完全二叉树
        root = constructBinaryTree.reConstructBinaryTree2(in1,post1);
        // 根据中序和后序重建二叉树后打印前序序列
        System.out.println("根据中序和后序重建二叉树后打印前序序列：");
        constructBinaryTree.preTraverse(root);

        // 对于斜树
        root2 = constructBinaryTree.reConstructBinaryTree2(in2,post2);
        constructBinaryTree.preTraverse(root2); // 1 2 3 4

        // 对于单节点树
        root3 = constructBinaryTree.reConstructBinaryTree2(in3,post3);
        constructBinaryTree.preTraverse(root3);

        // 对于序列不匹配的情况
        root4 = constructBinaryTree.reConstructBinaryTree2(in4,post4);
        constructBinaryTree.preTraverse(root4);


    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if(pre == null || in == null)
            return null;
        return reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
    }

    // 根据前序和中序序列重建二叉树，这是稳健的代码，可以解决序列不匹配的问题
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        // 前序遍历的第一个数字就是根节点的值
        TreeNode root = new TreeNode(pre[startPre]);
        if (startPre == endPre) { // 子树只有一个节点时
            if(startIn == endIn && pre[startPre] == in[startIn])
                return root;
            else
                throw new IllegalArgumentException("invalid input!");
        }
        // 在中序遍历序列中找到根节点的值
        int rootIn = startIn;
        while(rootIn <= endIn && in[rootIn] != pre[startPre])
            rootIn++;
        if(rootIn == endIn && in[rootIn] != pre[startPre]) // 只有一个节点时
            throw new IllegalArgumentException("invalid input!");
        // 对于中序序列，由前序序列确定根节点后，左子树的节点个数为 rootIn-startIn,
        // 所以对于前序序列左右子树的区间分别为[startPre+1,startPre+rootIn-startIn],[startPre+rootIn-startIn+1,endPre]
        // 而中序序列左右子树的区间为[startIn,rootIn-1],[rootIn+1,endIn]
        int leftLength = rootIn - startIn;
        if(leftLength > 0)
            root.left = reConstructBinaryTree(pre,startPre+1,startPre+leftLength,in,startIn,rootIn-1);
        if(leftLength < endPre - startPre)
            root.right = reConstructBinaryTree(pre,startPre+leftLength+1,endPre,in,rootIn+1,endIn);
        return root;
    }

    // 根据中序和后序序列重建二叉树
    public TreeNode reConstructBinaryTree2(int[] in, int[] post) {
        if(in == null || post == null)
            return null;
        return reConstructBinaryTree2(in,0,in.length-1,post,0,post.length-1);
    }

    private TreeNode reConstructBinaryTree2(int[] in, int startIn, int endIn, int[] post, int startPost, int endPost) {
        // 下面的两个if语句顺序不能变，否则对于斜树的情况会抛出异常
        if(startIn > endIn || startPost > endPost) // 解决斜树的情况
            return null;
        if(endIn < 0 || endPost < 0)
            throw new IllegalArgumentException("invalid input!");
        TreeNode root = new TreeNode(post[endPost]);
        for(int i=0; i<=endIn; i++) {
            if(post[endPost] == in[i]) {
                // 左子树的节点个数i-startIn,在后序序列中，左右子树区间分别为[startPost,startPost+i-startIn-1],[startPost+i-startIn,endPost-1]
                root.left = reConstructBinaryTree2(in, startIn, i - 1, post, startPost, startPost + i - startIn - 1);
                root.right = reConstructBinaryTree2(in, i + 1, endIn, post, startPost + i - startIn, endPost - 1);
            }
        }
        return root;
    }

    private void preTraverse(TreeNode node) {
        if(node == null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.val + " ");
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    private void postTraverse(TreeNode node) {
        TreeNode lastVisitedNode = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }else {
                TreeNode peekNode = stack.peek();
                if(peekNode.right != null && peekNode.right != lastVisitedNode)
                    node = peekNode.right;
                else {
                    System.out.print(peekNode.val + " ");
                    lastVisitedNode = stack.pop();
                }
            }
        }
        System.out.println();
    }
}
