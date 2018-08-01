package dynamic_programming;

/**
 *  排成一条线的硬币博弈问题
 *  minmax 算法思想
 */
public class CoinGame {

    /**
     *	得到硬币博弈问题的获胜分值
     *	输入：代表硬币排列情况的数组arr
     *	返回：硬币博弈问题的获胜分值
     *
     *  用dp[i][j]表示i,j区间内先拿硬币能得到的最大值.因为两个玩家都按照最优策略来玩
     *
     *  dp[i][j] = max{ min{arr[i]+dp[i+2][j], arr[i]+dp[i+1][j-1]}, min{arr[j]+dp[i][j-2], arr[j]+dp[i+1][j-1]}} i<j
     *           = 0     , i>j
     *           = arr[i], i=j
     */
    public static int getWinValue(int[] arr) {
        if (arr.length==1)
            return arr[0];

        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+= arr[i];
        }
        int dp[][] = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = arr[i];
        }

        for (int i = arr.length-1; i >= 0 ; i--) {
            for (int j = i+1; j < arr.length; j++) {

                int a=0,b=0,c=0;

                if (i+2<arr.length)
                    a = dp[i+2][j];

                if (i+1<arr.length&&j-1>=0)
                    b = dp[i+1][j-1];

                if (j-2>=0)
                    c = dp[i][j-2];

                dp[i][j] = java.lang.Math.max(java.lang.Math.min(a+arr[i],b+arr[i]), java.lang.Math.min(b+arr[j],c+arr[j]));
            }
        }

        return java.lang.Math.max(sum-dp[0][arr.length-1], dp[0][arr.length-1]);
    }

    public static void main(String[] args) {
        System.out.println(getWinValue(new int[]{1,2,3,4,5}));
    }

}
