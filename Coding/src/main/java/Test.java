import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void m1(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char arr[] = s.toCharArray();
        int k = arr.length/4;
        System.out.println(s.substring(0,k+1));
        int p = k+1;
        int q = arr.length-1;
        for (int i=0;i<=k-2;i++){
            StringBuilder builder = new StringBuilder();
            builder.append(arr[q]);
            for (int j = 0; j < k - 1; j++) {
                builder.append(" ");
            }
            builder.append(arr[p]);
            p++;
            q--;
            System.out.println(builder.toString());
        }
        StringBuilder builder = new StringBuilder();
        while (q>=p){
            builder.append(arr[q]);
            q--;
        }
        System.out.println(builder.toString());
    }

    public static void m2(){
        Scanner scanner = new Scanner(System.in);
        String numberString = scanner.nextLine();
        int splitIndex = 1;
        int total = 0;
        for (; splitIndex < numberString.length(); splitIndex++) {
            String left = numberString.substring(0,splitIndex);
            String right = numberString.substring(splitIndex);

            //两个都是整数
            total += validInt(left)*validInt(right);
            //left整数 right小数
            total += validInt(left)*validFloat(right);
            //left小数 right整数
            total += validFloat(left)*validInt(right);
            //left right都为小数

            total += validFloat(left)*validFloat(right);
        }
        System.out.println(total);
    }

    private static int validInt(String target){
        int preZeros=0;
        for (int i = 0; i < target.length() ; i++) {
            if (target.charAt(i)=='0'){
                preZeros++;
            }else {
                break;
            }
        }
        if (preZeros>0&&target.length()>1){
            return 0;
        }else {
            return 1;
        }
    }

    private static int validFloat(String target){
        if (target.length()==1){
            return 0;
        }
        int splitIndex= 1;
        int total = 0;
        for (; splitIndex < target.length(); splitIndex++) {
            String left = target.substring(0,splitIndex);
            String right = target.substring(splitIndex);
            if (validPart(left,right)){
                total++;
            }
        }
        return total;
    }

    private static boolean validPart(String left, String right){
        boolean validLeft = true;
        int preZeros=0;
        for (int i = 0; i < left.length() ; i++) {
            if (left.charAt(i)=='0'){
                preZeros++;
            }else {
                break;
            }
        }
        if (preZeros>0&&left.length()>1){
            validLeft = false;
        }else {
            validLeft = true;
        }

        boolean validRight = true;
        for (int i = right.length()-1; i>=0 ; i--) {
            if (right.charAt(i)=='0') {
                validRight = false;
                break;
            }else {
                break;
            }
        }

        return validLeft&&validRight;
    }

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
