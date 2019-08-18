import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 序列化二叉树
 * @author: Daniel
 * @create: 2019-03-09-21-49
 **/
public class SerializeBinaryTrees_37 {

    // 利用层序遍历序列化
    public static String serialize(TreeNode root) {
        if(root == null)
            return null;
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            root = queue.poll();
            if(root != null) {
                sb.append(root.val);
                sb.append(",");
                queue.offer(root.left);
                queue.offer(root.right); // 队列允许添加null
            }else {
                sb.append("#,");
            }
        }
        sb.deleteCharAt(sb.length()-1); // 删除末尾的,号
        return sb.toString();
    }

    // 反序列化
    public static TreeNode deserialize(String str) {
        if(str == null || str.length() == 0)
            return null;
        String[] strs = str.split(",");
        TreeNode[] nodes = new TreeNode[strs.length];
        for(int i=0; i<nodes.length; i++) {
            if(!strs[i].equals("#"))
                nodes[i] = new TreeNode(Integer.valueOf(strs[i]));
        }
        for(int i=0,j=1; j<nodes.length; i++) {
            if(nodes[i] != null) {
                nodes[i].left = nodes[j++];
                nodes[i].right = nodes[j++];
            }
        }
        return nodes[0];
    }

    public static void main(String[] args) {
        test1();
    }

    //    1
    //   2  3
    // 4   5  6
    public static void test1() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        connect(node1,node2,node3);
        connect(node2,node4,null);
        connect(node3,node5,node6);

        String str = serialize(node1);
        System.out.println(str); // 1,2,3,4,#,5,6,#,#,#,#,#,#
        TreeNode root = deserialize(str);
        postTraverse(root); // 4 2 5 6 3 1
    }

    public static void connect(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root != null) {
            root.left = node1;
            root.right = node2;
        }
    }

    public static void postTraverse(TreeNode root) {
        if(root == null)
            return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisitedNode = null;
        while(!stack.isEmpty() || root != null) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            }else {
                TreeNode temp = stack.peek();
                if(temp.right != null && temp.right != lastVisitedNode)
                    root = temp.right;
                else {
                    System.out.print(temp.val + " ");
                    lastVisitedNode = stack.pop();
                }
            }
        }
        System.out.println();
    }
}
