import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 从上到下打印二叉树 广度优先遍历
 * @author: Daniel
 * @create: 2019-03-07-09-42
 **/
public class PrintTreeFromTopToBottom_32 {
    // 不分行从上到下打印二叉树
    public static ArrayList<Integer> printFromTopToBottom(TreeNode node) {
        ArrayList<Integer> result = new ArrayList<>();
        if(node == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            node = queue.poll();
            result.add(node.val);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return result;
    }
    // 分行从上往下打印二叉树
    public static ArrayList<ArrayList<Integer>> printlnFromTopToBottom(TreeNode node) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(node == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size-->0) {
                node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }

    // 之字型从上到下打印二叉树，即奇数行从左往右打印，偶数行从右往左打印
    // 可以利用LinkedList底层是双向链表，可以实现倒序遍历，只需要判断该行是奇偶即可
    public static ArrayList<ArrayList<Integer>> printZFromTopToBottom(TreeNode node) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(node == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            ArrayList<Integer> list = new ArrayList<>();
            if((level & 1) == 1) { // 奇数行正序遍历
                Iterator<TreeNode> it = queue.iterator();
                while(it.hasNext())
                    list.add(it.next().val);
            }else { // 偶数行倒序遍历
                Iterator<TreeNode> it = queue.descendingIterator();
                while(it.hasNext())
                    list.add(it.next().val);
            }
            int size = queue.size();
            while(size-->0) {
                node = queue.poll();
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }

    // 深度优先遍历从上到下打印二叉树
    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return result;
        dfs(root,result,0); // 从第0层开始
        return result;
    }

    public static void dfs(TreeNode node, ArrayList<ArrayList<Integer>> wrapList, int level) {
        if(node == null)
            return;
        if(wrapList.size() < level + 1)
            wrapList.add(new ArrayList<>()); // 先添加一个空的ArrayList，便于后面的插入，这有别于bfs的插入
        wrapList.get(level).add(node.val);

        dfs(node.left,wrapList,level+1);
        dfs(node.right,wrapList,level+1);
    }

    public static void main(String[] args) {
        test1();
    }

    //     5
    //   4   7
    //  3   6  8
    public static void test1() {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);

        connect(node5,node4,node7);
        connect(node4,node3,null);
        connect(node7,node6,node8);

        System.out.println(printFromTopToBottom(node5));
        System.out.println(printlnFromTopToBottom(node5));
        System.out.println(printZFromTopToBottom(node5));
        System.out.println(levelOrder(node5));
    }

    public static void connect(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root != null) {
            root.left = node1;
            root.right = node2;
        }
    }
}
