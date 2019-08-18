import java.util.LinkedList;

/**
 * @description: 平衡二叉树
 * 平衡二叉树，又称AVL树，它是一种特殊的二叉排序树。AVL树或者是一棵空树，或者是具有以下性质的二叉树：
 *
 * （1）左子树和右子树都是平衡二叉树；
 *
 * （2）左子树和右子树的深度（高度）之差的绝对值不超过1。
 *
 * https://www.cnblogs.com/sench/p/7786718.html
 * @author: Daniel
 * @create: 2019-03-26-17-41
 **/
public class AVL {
    class Node {
        int key;
        int height; // 节点的高度，根节点为0
        Node left;
        Node right;

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }

    Node root; // 根节点

    private int height(Node node) {
        if(node != null)
            return node.height;
        return 0;
    }

    /**
     *  左左旋转
     *  插入或删除一个节点后，根节点的左子树的左子树还有非空子节点，导致"根的左子树的高度"比"根的右子树的高度"大2，AVL树失去了平衡。
     *
     *  二叉树失衡的原因是因为结点1的存在，而结点1位于结点8“左子树的左子树”，所以要使用LL旋转来调节，只需一次旋转即可达到平衡。
     *  具体的方法是：LL旋转的对象是“最低失衡根结点”，也就是结点8，找到8的左孩子4，将4的右孩子6变成8的左孩子，最后将8变成4的右孩子
     *         8                            4
     *      4     12       ==>           2     8
     *    2   6                        1     6   12
     *  1
     */
    private Node leftLeftRotate(Node node) {
        Node lchild = node.left;
        node.left = lchild.right;
        lchild.right = node;

        lchild.height = Math.max(height(lchild.left),height(node)) + 1;
        node.height = Math.max(height(node.left),height(node.right)) + 1;

        return lchild;
    }

    /**
     * 右右旋转
     * 插入或删除一个节点后，根节点的右子树的右子树还有非空子节点，导致"根的右子树的高度"比"根的左子树的高度"大2，AVL树失去了平衡。
     *
     * 二叉树的失衡是结点13导致的，而结点13位于结点8“右子树的右子树”，所以要使用RR旋转来调节，只需一次旋转即可达到平衡。
     * 方法与LL旋转类似：RR旋转的对象是“最低失衡根结点”，这里是结点8，找到8的右孩子12，将12的左孩子10变成8的右孩子，最后将8变成12的左孩子
     *        8                                  12
     *     4     12            ==>            8      14
     *         10   14                      4   10  13
     *             13
     */
    private Node rightRightRotate(Node node) {
        Node rchild = node.right;
        node.right = rchild.left;
        rchild.left = node;

        rchild.height = Math.max(height(node), height(rchild.right)) + 1;
        node.height = Math.max(height(node.left),height(node.right)) + 1;

        return rchild;
    }

    /**
     * 左右旋转(两次旋转)
     * 插入或删除一个节点后，根节点的左子树的右子树还有非空子节点，导致"根的左子树的高度"比"根的右子树的高度"大2，AVL树失去了平衡。
     *
     * 二叉树失衡是因为结点5的存在，结点5位于结点8“左子树的右子树”，所以使用LR旋转来调节。方法：
     * （1）先对最低失衡根结点的左孩子（结点4）进行RR旋转；
     * （2）再对最低失衡根结点（结点8）进行LL旋转。
     *         8       对4进行RR旋转       8       对8进行LL旋转          6
     *      4     12       ==>         6     12     ==>               4    8
     *    2   6                     4                               2   5    12
     *       5                    2   5
     */
    private Node leftRightRotate(Node node) {
        node.left = rightRightRotate(node.left); // 先对左子树右右旋转
        return leftLeftRotate(node); // 再对根节点左左旋转
    }

    /**
     * 右左旋转
     * 插入或删除一个节点后，根节点的右子树的左子树还有非空子节点，导致"根的右子树的高度"比"根的左子树的高度"大2，AVL树失去了平衡。
     *
     * 旋转步骤：
     * （1）先对最低失衡结点右孩子（结点12）LL旋转；
     * （2）在对最低失衡结点（结点8）RR旋转
     *        8              对12进行LL旋转          8              对8进行RR旋转        10
     *     4     12             ==>              4     10            ==>             8     12
     *         10   14                               9    12                       4   9      14
     *        9                                              14
     */
    private Node rightLeftRotate(Node node) {
        node.right = leftLeftRotate(node.right);
        return rightRightRotate(node);
    }

    public void insert(int key) {
        root = insert(root,key);
    }

    private Node insert(Node node, int key) {
        if(node == null)
            node = new Node(key,null,null);
        else if(key < node.key) { // 插入左子树
            node.left = insert(node.left,key);
            if(height(node.left) - height(node.right) == 2) { // 插入导致二叉树失衡
                if(key < node.left.key)
                    node = leftLeftRotate(node);
                else
                    node = leftRightRotate(node);
            }
        }else if(key > node.key) { // 插入右子树
            node.right = insert(node.right,key);
            if(height(node.right) - height(node.left) == 2) { // 插入导致二叉树失衡
                if(key > node.right.key)
                    node = rightRightRotate(node);
                else
                    node = rightLeftRotate(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void delete(int key) {
        if(root == null)
            throw new RuntimeException("the tree is empty");
        root = delete(root,key);
    }

    private Node delete(Node node, int key) {
        if(node.key > key) { // 要删除的节点在左子树
            node.left = delete(node.left,key);
            if(height(node.right) - height(node.left) == 2) { // 删除导致二叉树失衡
                Node right = node.right;
                if(height(right.left) > height(right.right))
                    node = rightLeftRotate(node);
                else
                    node = rightRightRotate(node);
            }
        }else if(node.key < key) { // 要删除的节点在右子树
            node.right = delete(node.right,key);
            if(height(node.left) - height(node.right) == 2) { // 删除导致二叉树失衡
                Node left = node.left;
                if(height(left.left) > height(left.right))
                    node = leftLeftRotate(node);
                else
                    node = leftRightRotate(node);
            }
        }else { // 找到了要删除的节点
            if(node.left != null && node.right != null) { // 节点的左右节点都不为空
                if(height(node.left) > height(node.right)) {
                    /**
                     * 如果node的左子树比右子树高，则
                     * 1. 找出node的左子树中的最大节点
                     * 2. 将该最大节点的值复制给node
                     * 3. 删除该最大节点
                     * 用左子树中的最大节点替代node的好处是，删除node的左子树中的最大节点后，AVL树依然是平衡的
                     */
                    Node max = node.left;
                    while (max.right != null)
                        max = max.right;
                    node.key = max.key;
                    node.left = delete(node.left, max.key);
                }else {
                    /**
                     * 如果node的左子树不比右子树高（相等，或右子树比左子树高），则
                     * 1. 找出node的右子树中的最小节点
                     * 2. 将该最小节点的值赋给node
                     * 3. 删除该最小节点
                     * 用右子树中的最小节点代替node的好处是，删除node的右子树中的最小节点后，AVL树依然是平衡的
                     */
                    Node min = node.right;
                    while(min.left != null)
                        min = min.left;
                    node.key = min.key;
                    node.right = delete(node.right,min.key);
                }
            }else { // 叶子节点或者只有一个子节点
                Node tmp = node;
                node = node.left != null ? node.left : node.right;
                tmp.left = null;
                tmp.right = null;
            }
        }
        return node;
    }

    // 按树形打印二叉树
    public void printTree() {
        System.out.println("树形打印二叉树：");
        LinkedList<Node> globalStack = new LinkedList<>();
        globalStack.push(root);
        int nBlack = 32; // 树大的时候可以增大这个值
        boolean isEmptyRow = false;
        int count = 0; // 记录同层节点的个数
        int size = 0;
        while(!isEmptyRow) {
            LinkedList<Node> localStack = new LinkedList<>();
            isEmptyRow = true;
            count = 0; // 重新记录
            size = globalStack.size();
            for(int i=0; i<nBlack; i++)
                System.out.print(" ");
            while(!globalStack.isEmpty()) {
                // 里面的while循环用于查看全局的栈是否为空
                Node temp = globalStack.pop();
                count++;
                if(temp != null) {
                    System.out.print(temp.key);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    // 如果当前的节点下面还有子节点，则必须要进行下一层的循环
                    if (temp.left != null || temp.right != null)
                        isEmptyRow = false; // 叶子节点不会改变其值
                }
                else {
                    System.out.print("# ");
                    localStack.push(null);
                    localStack.push(null);
                }
                // 打印一些空格
                if(count < size) // 最右边的节点不打印空格
                    for(int i=0; i<nBlack*2-2; i++) { // 同层节点之间的距离，根据打印结果自行调整
                        System.out.print("-");
                    }
            }// 内循环结束

            System.out.println();
            nBlack /= 2;
            while(!localStack.isEmpty())
                globalStack.push(localStack.pop());

        } // 外循环结束
        System.out.println();
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] nodes = {1,2,3,4,5,6,7,8,9,10,11};
        for(int node : nodes)
            avl.insert(node);
        avl.printTree();
        avl.delete(4);
        avl.printTree();
    }
}
