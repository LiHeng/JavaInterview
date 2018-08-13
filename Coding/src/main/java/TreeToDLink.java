public class TreeToDLink {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static TreeNode convert(TreeNode root){
        TreeNode tail = convert(root,null);
        while (tail!=null&&tail.left!=null){
            tail = tail.left;
        }
        return tail;
    }

    //需要一个变量来表示形成的链表的最后一个节点
    public static TreeNode convert(TreeNode root,TreeNode lastOfList){
        if (root==null)
            return null;
        if (root.left!=null) {
            lastOfList = convert(root.left, lastOfList);
        }
        root.left = lastOfList;
        if (lastOfList!=null)
            lastOfList.right = root;
        lastOfList = root;
        if (root.right!=null){
            lastOfList = convert(root.right, lastOfList);
        }
        return lastOfList;
    }

}
