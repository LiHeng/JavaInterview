public class Trie {

    private class TrieNode{
        private int num; // 有多少单词通过这个节点，即由根至改节点组成的字符窜模式出现的次数
        private TrieNode[] son;  // 所有的儿子节点
        private boolean isEnd;   // 是不是最后一个节点(叶节点)
        private char val;   // 节点的值

        TrieNode(){
            num = 1;
            son = new TrieNode[SIZE];
            isEnd = false;
        }
    }

    private static final int SIZE = 26;   //字符集的大小
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    //插入字典树
    public void insert(String str){
        if (str==null||str.isEmpty()){
            return;
        }

        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int pos = letters[i]-'a';
            if (node.son[pos]==null){
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            }else {
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    //计算前缀的数量
    public int countPrefix(String prefix){
        if (prefix==null||prefix.isEmpty()){
            return 0;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int pos = letters[i]-'a';
            if (node.son[pos]==null)
                return 0;
            else
                node = node.son[pos];
        }
        return node.num;
    }

    //
    public String hasPrefix(String prefix){
        if (prefix==null||prefix.isEmpty()){
            return null;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int pos = letters[i]-'a';
            if (node.son[pos]==null)
                return null;
            else
                node = node.son[pos];
        }
        preTraverse(node, prefix);
        return null;
    }

    public TrieNode getRoot() {
        return root;
    }

    private void preTraverse(TrieNode node, String prefix){
        if (!node.isEnd){
            for (TrieNode child:node.son){
                if (child!=null){
                    preTraverse(child,prefix+child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }

    //字符窜完全匹配
    public boolean has(String str){
        if (str==null||str.isEmpty()){
            return false;
        }

        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int pos = letters[i]-'a';
            if (node.son[pos]==null)
                return false;
            else
                node = node.son[pos];
        }
        return node.isEnd;
    }


    public void preTraverse(TrieNode node){
        if (node!=null){
            if (node!=root) {
                System.out.print(""+node.val + '-');
            }
            for (TrieNode child :node.son) {
                preTraverse(child);
            }
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] strs = {"banana","band","bee","absolute","acm"};
        String[] prefix = {"ba", "b", "band", "abc"};
        for (String str : strs) {
            trie.insert(str);
        }
        System.out.println(trie.has("abc"));
        trie.preTraverse(trie.getRoot());
        System.out.println();
        for (String pre : prefix) {
            int num = trie.countPrefix(pre);
            System.out.println(pre+" "+num);
        }
    }

}
