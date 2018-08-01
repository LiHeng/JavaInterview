package dynamic_programming;

/**
 * 动态规划: 求解最长回文子串
 * 例如 "babad" 的最长回文子串为 "bab" 或者 "aba"
 *
 * dp[i,j]表示子串 s[i,...,j]是否为回文字符串
 * dp[i,j] = (s[i]==s[j])&&dp[i+1][j-1],  i+1<j
 *           s[i]==s[j]                ,  i+1=j
 *           true                      ,  i=j
 */
public class MaxSubPalindromicString {

    public static String maxString(String s){
        int maxLen = 1;
        int start = 0;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int i = s.length()-1; i >=0; i--) {
            dp[i][i] = true;
            for (int j = i+1; j < s.length(); j++) {
                if(i+1<=j-1)
                    dp[i][j] = (s.charAt(i)==s.charAt(j))&&dp[i+1][j-1];
                else
                    dp[i][j] = (s.charAt(i)==s.charAt(j));
                if (dp[i][j] && j-i+1>maxLen){
                    maxLen = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start, start+maxLen);
    }

    public static void main(String[] args) {
        System.out.println(maxString("a"));
    }
}
