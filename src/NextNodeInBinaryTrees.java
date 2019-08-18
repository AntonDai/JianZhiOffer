/**
 * @description: 二叉树的下一个节点（中序遍历下的后继节点）前提：给定的二叉树有指向父节点的指针
 * 思路：1. 如果当前节点cur有右子树，则沿着它的右子节点的左子树一直往下，最左子节点就是下一个节点；
 *       2. 如果cur没有右子树
 *          1）若cur是其父节点的左孩子，则其父节点就是它的下一个节点
 *          2）若cur是其父节点的右孩子，则沿着它指向父节点的指针一直往上，直到找到一个是它父节点的左子节点的节点，
 *              如果存在这样的节点，则它的父节点就是我们要找的下一个节点，如果不存在，比如一直往上遍历到了根节点，它没有父节点，则cur没有下一个节点。
 *
 * 补充：如果求中序遍历下的前驱节点？ 还是同样的分析
 *
 * 问题：如果没有指向父节点的指针呢
 *
 * @author: Daniel
 * @create: 2019-02-16-14-42
 **/
public class NextNodeInBinaryTrees {
    public static void main(String[] args) {
        NextNodeInBinaryTrees nextNodeInBinaryTrees = new NextNodeInBinaryTrees();
        nextNodeInBinaryTrees.test1();
        nextNodeInBinaryTrees.test2();
        nextNodeInBinaryTrees.test3();
    }

    public TreeLinkNode getNext(TreeLinkNode node) {
        if(node == null)
            return null;
        if(node.right != null) { // 如果存在右节点，沿着该右节点的左孩子一直往下找
            node = node.right;
            while(node.left != null)
                node = node.left;
            return node; // 此时的node.left == null
        }
        while(node.parent != null) { // 没有右节点，则沿着父节点往上找第一个它父节点的左子节点的节点
            TreeLinkNode nodeParent = node.parent;
            if(nodeParent.left == node)
                return nodeParent;
            node = node.parent;
        }
        return null; // 退到了根节点还没找到，返回null，没有下一个节点
    }

    // 辅助代码用于构建二叉树
    public void connectTreeNodes(TreeLinkNode parent, TreeLinkNode left, TreeLinkNode right) {
        if(parent != null) {
            parent.left = left;
            parent.right = right;
            if(left != null)
                left.parent = parent;
            if(right != null)
                right.parent = parent;
        }
    }

    /** 测试代码*/

    public void test(String testId,TreeLinkNode node, TreeLinkNode expected) {
        System.out.print(testId + ": ");
        TreeLinkNode next = getNext(node);
        if(next == expected)
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }

    // 完全二叉树
    //            8
    //        6      10
    //       5 7    9  11
    public void test1() {
        TreeLinkNode node8 = new TreeLinkNode(8);
        TreeLinkNode node6 = new TreeLinkNode(6);
        TreeLinkNode node10 = new TreeLinkNode(10);
        TreeLinkNode node5 = new TreeLinkNode(5);
        TreeLinkNode node7 = new TreeLinkNode(7);
        TreeLinkNode node9 = new TreeLinkNode(9);
        TreeLinkNode node11 = new TreeLinkNode(11);

        connectTreeNodes(node8,node6,node10);
        connectTreeNodes(node6,node5,node7);
        connectTreeNodes(node10,node9,node11);

        test("test1",node8,node9);
        test("test1",node6,node7);
        test("test1",node10,node11);
        test("test1",node5,node6);
        test("test1",node7,node8);
        test("test1",node9,node10);
        test("test1",node11,null);
    }

    // 斜树
    //            5
    //          4
    //        3
    //      2
    public void test2() {
        TreeLinkNode node5 = new TreeLinkNode(5);
        TreeLinkNode node4 = new TreeLinkNode(4);
        TreeLinkNode node3 = new TreeLinkNode(3);
        TreeLinkNode node2 = new TreeLinkNode(2);

        connectTreeNodes(node5,node4,null);
        connectTreeNodes(node4,node3,null);
        connectTreeNodes(node3,node2,null);

        test("test2",node5,null);
        test("test2",node4,node5);
        test("test2",node3,node4);
        test("test2",node2,node3);
    }

    // 单节点树
    // 5
    public void test3() {
        TreeLinkNode node5 = new TreeLinkNode(5);
        test("test3",node5,null);
    }
}
