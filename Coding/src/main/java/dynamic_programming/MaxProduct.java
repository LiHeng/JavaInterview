package dynamic_programming;

/**
 * 动态规划求解：一个自然数分解为n个数的和，求n个数积最大
 *
 * dp[n] = max{ dp[n-a] * dp[a] } 1<=a<=n/2,   n>4
 *         n                                   n<=4
 *
 * 时间复杂度: O(n^2)
 * 空间复杂度: O(n)
 */
public class MaxProduct {

    public static int maxProduct(int n){
        int dp[] = new int[n+1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 5; i <=n ; i++) {
            for (int j = 1; j <= i / 2; j++) {
                if (dp[j]*dp[i-j]>dp[i]){
                    dp[i] = dp[j]*dp[i-j];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(5));
        System.out.println(maxProduct(10));
        System.out.println(maxProduct(15));
        System.out.println(maxProduct(20));
        System.out.println(maxProduct(25));
    }
}
