/**
 * @description: 实现单词查找树
 * Trie 树又叫字典树、前缀树、单词查找树，它是一颗多叉查找树。与二叉查找树不同，键不是直接保存在节点中，而是由节点在树中的位置决定。
 * https://en.wikipedia.org/wiki/Trie
 * @author: Daniel
 * @create: 2019-03-13-08-48
 **/
public class Trie {
    private class Node {
        Node[] childs = new Node[26];
        boolean isLeaf;
    }

    private Node root = new Node();

    public Trie() {

    }

    public void insert(String word) {
        if(word == null || word.length() == 0)
            return;
        insert(root,word);
    }

    private void insert(Node node, String word) {
        if(node == null)
            return;
        if(word.length() == 0) {
            node.isLeaf = true;
            return;
        }
        int index = indexForChar(word.charAt(0));
        if(node.childs[index] == null)
            node.childs[index] = new Node();
        insert(node.childs[index],word.substring(1));
    }

    public boolean search(String word) {
        if(word == null || word.length() == 0)
            return false;
        return search(root,word);
    }

    // 查找是否是某个前缀
    public boolean startWith(String prefix) {
        if(prefix == null || prefix.length() == 0)
            return false;
        return startWith(root,prefix);
    }

    private boolean startWith(Node node, String prefix) {
        if(node == null)
            return false;
        if(prefix.length() == 0)
            return true;
        int index = indexForChar(prefix.charAt(0));
        return startWith(node.childs[index],prefix.substring(1));
    }

    private boolean search(Node node, String word) {
        if(node == null)
            return false;
        if(word.length() == 0)
            return node.isLeaf;
        int index = indexForChar(word.charAt(0));
        return search(node.childs[index],word.substring(1));
    }

    private int indexForChar(char c) {
        return c - 'a'; // A的ASCII码是65,a的ASCII码是97
    }

    public static void main(String[] args) {
        String[] str = {"apple","dog","cat","car","small","big"};
        Trie trie = new Trie();
        for(String s : str)
            trie.insert(s);
        System.out.println(trie.search("smal"));
    }
}
