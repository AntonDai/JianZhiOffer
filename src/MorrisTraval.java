import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Morris 遍历，时间复杂度 O(h)或O(n) 空间复杂度 O(1)
 * @author: Daniel
 * @create: 2019-01-08 17:32
 **/
public class MorrisTraval {
    // 二叉树的节点类
    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    // 获得当前节点的前驱节点
    private TreeNode getPredecessor(TreeNode cur) {
        TreeNode pre = cur;
        if(cur.left != null) {
            pre = pre.left;
            while(pre.right != null && pre.right != cur)
                pre = pre.right;
        }
        return pre;
    }

    // 中序遍历
    public void inorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null) {
            if(cur.left == null) { // 1
                System.out.print(cur.val + " ");
                cur = cur.right; // 将其右孩子设置为当前节点
            } else {
                // 寻找前驱节点
                // 下面的两条语句等效于 TreeNode pre = getPredecessor(cur);
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null) { // 2.1
                    pre.right = cur; // 把前驱节点的右孩子指向当前节点
                    cur = cur.left; // 更新当前节点为当前节点的左孩子
                } else { // 2.2
                    pre.right = null; // 重新设为null
                    System.out.print(cur.val + " ");
                    cur = cur.right; // 更新当前节点为其右孩子
                }
            }
        }
        System.out.println();
    }

    // 前序遍历
    public void preorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root, pre = null;
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
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                }else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    // 倒序某两个节点之间路径上的各个节点
    private void reverse(TreeNode from, TreeNode to) {
        if(from == to) return;
        TreeNode x = from;
        TreeNode y = from.right; // 要保证from不为空
        TreeNode z = null;
        while(true) {
            z = y.right;
            y.right = x;
            x = y;
            y = z;
            if(x == to)
                break;
        }
    }

    // 倒序输出
    private void printReverse(TreeNode from, TreeNode to) {
        reverse(from,to);
        TreeNode p = to;
        while(true) {
            System.out.print(p.val + " ");
            if(p == from)
                break;
            p = p.right;
        }
        reverse(to,from);
    }

    // 后序遍历
    public void postorderMorrisTraversal(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        dump.left = root;
        TreeNode cur = dump, pre = null;
        while(cur != null) {
            if(cur.left == null) {
                cur = cur.right;
            }else {
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null) {
                    pre.right = cur; // 右孩子指向当前节点
                    cur = cur.left;
                } else { // pre.right == cur
                    pre.right = null; // 本身就是叶子节点
                    printReverse(cur.left,pre);
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    // 按树形打印二叉树
    public void printTree(TreeNode root) {
        System.out.println("树形打印二叉树：");
        LinkedList<TreeNode> globalStack = new LinkedList<>();
        globalStack.push(root);
        int nBlack = 32; // 树大的时候可以增大这个值
        boolean isEmptyRow = false;
        int count = 0; // 记录同层节点的个数
        int size = 0;
        while(!isEmptyRow) {
            LinkedList<TreeNode> localStack = new LinkedList<>();
            isEmptyRow = true;
            count = 0; // 重新记录
            size = globalStack.size();
            for(int i=0; i<nBlack; i++)
                System.out.print(" ");
            while(!globalStack.isEmpty()) {
                // 里面的while循环用于查看全局的栈是否为空
                TreeNode temp = globalStack.pop();
                count++;
                if(temp != null) {
                    System.out.print(temp.val);
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

    // 层次遍历分行打印二叉树
    private void printLevelOrder(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int count = 0;
            int size = queue.size();
            while(count < size) {
                TreeNode node = queue.poll();
                count++;
                System.out.print(node.val + " ");
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            System.out.println();
        }
    }

    // 将二叉搜索树转变成双向链表，沿着首节点right指针一直向下是升序，沿着尾结点left指针一直向下是降序，注意首节点是最小的节点，其左指针为空，尾节点是最大的节点，其右指针为空
    public TreeNode convert(TreeNode pRootOfTree) { // 由于没有创建新的节点，该方法会直接修改树的结构，所以一旦调用该方法，原来的树就不存在了
        TreeNode p = pRootOfTree, pre = null, res = null; // 当前节点，前驱节点，结果集 result
        while(p != null) {
            while(p.left != null) {
                TreeNode q = p.left;
                while(q.right != null)
                    q = q.right; // 如果当前节点存在左孩子，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点
                q.right = p; // 当前驱节点的右孩子为空的时候，将前驱节点的右孩子指向当前节点
                TreeNode tmp = p.left;
                p.left = null; // 将当前节点的左孩子置为空
                p = tmp; // 将当前节点更新为它的左孩子
            }
            // 运行到这里时，当前节点的左孩子为空
            p.left = pre;
            if(pre == null)
                res = p; // 没有前驱节点，自己就是首节点
            else
                pre.right = p; // 前驱节点的右孩子指向当前节点，这句只有在更新根节点的右节点时才发挥作用，其他时候其实都是重复操作
            pre = p; // 将前驱节点更新为当前节点
            p = p.right; // 将右孩子作为当前节点
        }
        return res;
    }

    // 下面是递归版的二叉树转化双向链表
    // 双向链表的左边头结点和右边头节点，注意这里不是创建新的节点而是多个引用指向同一个对象
    TreeNode leftHead = null;
    TreeNode rightHead = null;
    public TreeNode convertRecursion(TreeNode pRootOfTree) {
        // 递归调用叶子节点的左右节点返回空
        if(pRootOfTree == null) return null;
        convertRecursion(pRootOfTree.left);  // 会一直到最左边的叶子节点结束，接下来使其成为链表的第一个节点
        if(rightHead == null) { // 第一次
            leftHead = pRootOfTree;
            rightHead = pRootOfTree;
        } else {
            // 把当前节点插入到双向链表右边，rightHead向后移动
            rightHead.right = pRootOfTree; // 把当前节点插入到双向链表的当前头的右边
            pRootOfTree.left = rightHead; // 把当前节点的左孩子设为双向链表的当前头，从而实现双向
            rightHead = pRootOfTree; // 跳到下一个节点
        }
        // 把右叶子节点也插入到双向链表（rightHead已确定，直接插入）
        convertRecursion(pRootOfTree.right);
        return leftHead;
    }

    public static void main(String[] args){
        // 构造二叉树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(10);
        root.right.left.right = new TreeNode(8);

        MorrisTraval morrisTraval = new MorrisTraval();
        morrisTraval.printTree(root);
        System.out.println("层次遍历分行打印二叉树：");
        morrisTraval.printLevelOrder(root);
        System.out.println("中序遍历：");
        morrisTraval.inorderMorrisTraversal(root); // 中序遍历 1 2 3 4 5 6 7 8 9 10
        System.out.println("前序遍历：");
        morrisTraval.preorderMorrisTraversal(root); // 前序遍历 6 4 2 1 3 5 9 7 8 10
        System.out.println("后序遍历：");
        morrisTraval.postorderMorrisTraversal(root); // 后序遍历 1 3 2 5 4 8 7 10 9 6

        TreeNode head = morrisTraval.convert(root);
        System.out.println("从小到大输出为：");
        while(head.right != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
        System.out.println(head.val); // 输出最后一个节点
        System.out.println("从大到小输出为：");
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.left;
        } // 此时，head为空

        //morrisTraval.convertRecursion(root); // 要测试，必须先要注释掉上面的代码，因为上面的修改是直接操作原始的树的
    }
}
