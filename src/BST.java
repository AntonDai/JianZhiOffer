import java.util.LinkedList;

/**
 * @description: 二叉搜索树之删除节点
 * @author: Daniel
 * @create: 2019-03-24-22-15
 **/
public class BST<K extends Comparable<? super K>, V> {
    private TreeNode<K,V> root; // 根节点
    private int size;

    public BST() {
    }

    private class TreeNode<K extends Comparable<? super K>, V> {
        K key;
        V value;
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> parent;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public TreeNode(K key,V value, TreeNode<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }

    public void insert(K key, V value) {
       TreeNode<K,V> node = root;
       if(node == null) {
//           root = new TreeNode(key,value,null); // 定义了父指针
           root = new TreeNode(key,value);
           size = 1;
           return;
       }
       int cmp;
       TreeNode parent;
       do {
           parent = node;
           cmp = key.compareTo(node.key);
           if(cmp < 0)
               node = node.left;
           else if(cmp > 0)
               node = node.right;
           else
               node.value = value;
       }while(node != null);

//       TreeNode<K,V> e = new TreeNode<>(key,value,parent); // 定义了父指针
        TreeNode<K,V> e = new TreeNode<>(key,value);
       if(cmp < 0)
           parent.left = e;
       else
           parent.right = e;
       size++;
    }

    public V search(K key) {
        return search(root,key).value;
    }

    private TreeNode<K,V> search(TreeNode<K,V> node, K key) {
        if(node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            return search(node.left,key);
        else if(cmp > 0)
            return search(node.right,key);
        else
            return node;
    }

    /**
     * 删除节点存在 3 种情况
     * 1. 没有左右子节点，可以直接删除
     * 2. 存在左节点或者右节点，删除后需要对子节点移动
     * 3. 同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
     */
    // 如果树节点中定义有父指针
    public void delete(K key) {
        TreeNode<K,V> node = search(root,key);
        if(node != null)
            delete(node);
    }

    private void delete(TreeNode<K,V> node) {
        // 如果同时存在左右节点
        if(node.left != null && node.right != null) {
            // 获取后继节点，并替代待删除节点
            TreeNode<K,V> successor = node.right;
            while(successor.left != null)
                successor = successor.left;
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }
        // 经过前一步处理，下面只有两种情况：只有一个子节点或没有子节点
        TreeNode<K,V> child = null;
        if(node.left != null)
            child = node.left;
        else
            child = node.right;
        // 如果child不为空，说明只有一个子节点
        if(child != null) {
            child.parent = node.parent;
        }
        // 如果当前节点没有父节点，说明是根节点
        if(node.parent == null)
            // 将子节点设为根节点
            root = child;
        else if(node == node.parent.left) // 存在父节点，并且是左孩子
            // 将父节点的左孩子设为child
            node.parent.left = child;
        else // 存在父节点，并且是其右孩子
            node.parent.right = child;
        size--;
    }
    // 如果树节点中定义没有父指针
    public void delete2(K key) {
        root = delete(root,key);
    }

    private TreeNode<K,V> delete(TreeNode<K,V> node, K key) {
        if(node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            node.left = delete(node.left,key);
        else if(cmp > 0)
            node.right = delete(node.right,key);
        else {
            if(node.right == null)
                return node.left;
            if(node.left == null)
                return node.right;
            TreeNode tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        return node;
    }

    private TreeNode<K,V> min(TreeNode<K,V> node) {
        if(node.left == null)
            return node;
        return min(node.left);
    }
    // 不断深入根节点的左子树直至遇到一个空链接，然后将指向该节点的链接指向该节点的右子树
    private TreeNode<K,V> deleteMin(TreeNode<K,V> node) {
        if(node.left == null)
            return node.right;
        node.left = deleteMin(node.left); // 返回该节点的右链接，然后将该节点的右链接指向返回的节点，此时没有任何链接指向要删除的节点，因此它会被垃圾回收器清理掉
        return node;
    }

    // 按树形打印二叉树
    public void printTree() {
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
        BST<Integer,String> tree = new BST<>();
        tree.insert(4,"a");
        tree.insert(2,"b");
        tree.insert(3,"c");
        tree.insert(5,"d");
        tree.insert(1,"e");
        tree.insert(7,"f");
        tree.insert(6,"g");
        tree.insert(9,"h");
        tree.insert(8,"i");
        tree.printTree();
//        tree.delete(6);
        tree.delete2(6); // 需要修改insert函数
        tree.printTree();
//        tree.delete(4);
        tree.delete2(4);
        tree.printTree();
    }
}
