import java.util.LinkedList;

/**
 * @description: 二叉搜索树与双向链表
 * 在中序遍历的过程中，将遍历到的每个节点，串成链表，不同的是中序遍历的顺序是右→根→左。
 * 先遍历左子树的话，其链表生成的方向是小--->大，在链表生成完毕之后，记录指向的便是链表中的最后一个节点，也就是最大的那个节点。
 * 而先遍历右子树的话，其链表生成的方向是大---->小，在链表生成完毕之后，记录指向的便是链表中的最开始一个节点，也就是最小的那个节点。
 * 这样就可以减少一个记录引用。
 * @author: Daniel
 * @create: 2019-03-08-10-10
 **/
public class ConvertBinarySearchTree_36 {

    TreeNode head = null;

    public TreeNode convert(TreeNode root) {
        if(root == null) return null;
        convert(root.right); // 一直遍历到最右边的叶子节点，使其成为链表的第一个节点
        if(head == null) { // 第一次初始化
            head = root;
        }else {
            head.left = root;
            root.right = head;
            head = root; // 跳到下一个节点
        }
        convert(root.left);
        return head;
    }
    // 中序遍历稍微改动下
    public TreeNode convertIterative(TreeNode root) {
        if(root == null) return null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode head = null;
        while(!stack.isEmpty() || root != null) {
            if(root != null) {
                stack.push(root);
                root = root.right;
            }else {
                root = stack.pop();
                if(head == null) {
                    head = root;
                }else {
                    head.left = root;
                    root.right = head;
                    head = root;
                }
                root = root.left;
            }
        }
        return head;
    }

    public static TreeNode convertMorris(TreeNode root) {
        TreeNode cur = root, pre = null, head = null, tail = null; // 当前节点、前驱节点、双向链表的头结点和尾节点
        while(cur != null) {
            if(cur.left == null) {
                if(head == null) {
                    head = cur;
                    tail = cur;
                } else {
                    tail.right = cur;
                    cur.left = tail;
                    tail = cur;
                }
                cur = cur.right;
            }else {
                // 寻找前驱节点
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    tail = cur;
                    tail.left = pre;
                    cur = cur.right;
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {
        test1();
    }

    //    6
    //  4   8
    // 3 5 7 9
    public static void test1() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);

        connect(node6,node4,node8);
        connect(node4,node3,node5);
        connect(node8,node7,node9);

        TreeNode head = convertMorris(node6);
        print(head);
    }

    public static void connect(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root != null) {
            root.left = node1;
            root.right = node2;
        }
    }

    public static void print(TreeNode root) {
        while(root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
        System.out.println();
    }
}
