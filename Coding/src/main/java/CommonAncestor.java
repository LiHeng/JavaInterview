import java.util.ArrayList;
import java.util.List;

public class CommonAncestor {

    static class TreeNode {
        int val;
        List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    /**
     * 获取两个节点的最低公共祖先
	 */
    public static TreeNode getLastCommonParent(TreeNode root, TreeNode p1, TreeNode p2) {
        //path1和path2分别存储根节点到p1和p2的路径（不包括p1和p2）
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        getNodePath(root, p1, path1);
        getNodePath(root, p2, path2);
        //如果路径不存在，返回空
        if (path1.size() == 0 || path2.size() == 0)
            return null;

        return getLastCommonNode(path1, path2);
    }

    /**
     * 得到两个路径的最后一个公共节点
     */
    private static TreeNode getLastCommonNode(List<TreeNode> path1, List<TreeNode> path2) {
        TreeNode tmpNode = null;
        for (int i = 0; i < path1.size(); i++) {
            if (path1.get(i) != path2.get(i))
                break;
            tmpNode = path1.get(i);
        }

        return tmpNode;

    }

    /**
     * bfs 查找从根节点到目标节点的路径
     */
    private static boolean getNodePath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == target)
            return true;
        path.add(root);
        boolean found = false;
        for (TreeNode node : root.children) {
            found = getNodePath(node, target, path);
            if (found)
                break;
        }
        if (!found) {
            path.remove(path.size() - 1);
        }
        return found;
    }

    // 形状普通的树
    //             1
    //           /   \
    //         2      3
    //        /         \
    //      4            5
    //     / \        /  |  \
    //    6   7      8   9  10
    public static void test01() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.children.add(n2);
        n1.children.add(n3);

        n2.children.add(n4);

        n4.children.add(n6);
        n4.children.add(n7);

        n3.children.add(n5);

        n5.children.add(n8);
        n5.children.add(n9);
        n5.children.add(n10);

        System.out.println(getLastCommonParent(n1, n9, n10));
    }

    // 树退化成一个链表
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test02() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);

        System.out.println(getLastCommonParent(n1, n4, n5));
    }

    // 树退化成一个链表，一个结点不在树中
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test03() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);

        System.out.println(getLastCommonParent(n1, n5, n6));
    }


    public static void main(String[] args) {
        test01();
        System.out.println("==========");
        test02();
        System.out.println("==========");
        test03();

    }


}
