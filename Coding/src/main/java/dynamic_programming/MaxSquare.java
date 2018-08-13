package dynamic_programming;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing all 1's and return its area.
 * For example, given the following matrix:

 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 *
 *
 *  dp[i,j] 表示到达(i, j)位置所能组成的最大正方形的边长
 *  dp[i,j] = matrix[i][j]==1?1:0                           ,  i==0 || j==0
 *            0                                             ,  matrix[i][j]=0 i>0 j>0
 *            min{ dp[i-1][j-1], dp[i-1][j], dp[i][j-1] }+1 ,  i>0 j>0 matrix[i][j]=1
 */
public class MaxSquare {

    public static int maxSquare(int[][] matrix){
        int dp[][] = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i==0||j==0){
                    dp[i][j] = matrix[i][j]==1?1:0;
                }else{
                    if (matrix[i][j]==0){
                        dp[i][j] = 0;
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j-1], java.lang.Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    }
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max*max;
    }

    /**
     * 求矩阵从右下角到左上角的路径条数
     */
    public static int uniquePaths(int m, int n){
        int dp[][] = new int[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i<n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {1,0,0,1,0}
        };
        System.out.println(maxSquare(matrix));
        System.out.println(uniquePaths(7,3));
    }
}
