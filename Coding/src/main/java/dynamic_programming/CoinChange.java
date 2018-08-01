package dynamic_programming;

/**
 * 找零钱问题
 * dp[amount] = min{ dp[amount-coins[i]] + 1 } 0<=i<coins.len, amount>=1
 *              0                                            , amount=0
 *
 */

public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = -1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = coins.length-1; j >= 0; j--) {
                if (i>=coins[j]&&dp[i - coins[j]]!=-1) {
                    //如果当前钱数不可拼，或者有更简洁的拼法，更新当前硬币数
                    if (dp[i] == -1 || dp[i - coins[j]] + 1 < dp[i]) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2,5},3));
    }
}
