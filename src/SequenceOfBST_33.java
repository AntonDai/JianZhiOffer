/**
 * @description: 二叉搜索树的后序遍历序列
 * @author: Daniel
 * @create: 2019-03-01-08-06
 **/
public class            SequenceOfBST_33 {
    public boolean verifySequenceOfBST(int[] sequence) {
        if(sequence == null || sequence.length == 0)
            return false;
        return verifySequenceOfBST(sequence,0,sequence.length - 1);
    }

    public boolean verifySequenceOfBST(int[] sequence, int left, int right) {
        if(left >= right)
            return true;
        int i = right;
        while(i > left && sequence[i-1] > sequence[right]) // 在二叉搜索树中右子树的值大于根节点的值
            i--;
        int j = i - 1;
        while(j >= left) // 在二叉搜索树中左子树的值小于根节点的值
            if(sequence[j--] > sequence[right])
                return false;
        return verifySequenceOfBST(sequence,left,i-1) && verifySequenceOfBST(sequence,i,right-1);
    }

    public static void main(String[] args) {
        int[] sequence1 = {5,7,6,9,11,10,8};
        int[] sequence2 = {7,4,6,5};
        SequenceOfBST_33 sequenceOfBST33 = new SequenceOfBST_33();
        System.out.println(sequenceOfBST33.verifySequenceOfBST(sequence1)); // true
        System.out.println(sequenceOfBST33.verifySequenceOfBST(sequence2)); // false
    }
}
