/**
 * @description: 带有指向父节点的指针的树的定义
 * @author: Daniel
 * @create: 2019-02-16-14-40
 **/
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode parent = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
