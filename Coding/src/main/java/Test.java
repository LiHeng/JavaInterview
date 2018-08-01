import java.util.ArrayList;

public class Test {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSum(root, new ArrayList<>());
        return maxSum;
    }

    public void maxPathSum(TreeNode root, ArrayList<Integer> path) {
        if (root.left == null && root.right == null) {
            int sum = root.val;
            for (int a : path) {
                sum += a;
            }
            maxSum = Math.max(maxSum, sum);
        } else {
            path.add(root.val);
            if (root.left != null) {
                maxPathSum(root.left, new ArrayList<>(path));
            }
            if (root.right != null) {
                maxPathSum(root.right, new ArrayList<>(path));
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-3);
        System.out.println(new Test().maxPathSum(root));
    }
}
