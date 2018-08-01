package dynamic_programming;

/**
 * 动态规划: 求解最长回文子序列长度
 *
 * dp[i,j] = dp[i+1, j-1]+2,             , s[i]==s[j] i<j
 *           max{ dp[i+1, j], dp[i,j-1] }, s[i]!=s[j] i<j
 *           1                           , i==j
 *
 * 时间复杂度: O(n^2)
 * 空间复杂度: O(n^2)
 */

public class MaxSubPalindromicSequence {

    public static int maxSequence(String s){
        int dp[][] = new int[s.length()][s.length()];

        for (int i = s.length()-1; i>=0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1]+2;
                else
                    dp[i][j] = java.lang.Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(maxSequence("bbbab"));
    }
}
