package dynamic_programming;

import java.util.Scanner;

public class MaxHappiness {

    public static int maxHappiness(int[][] tree, int happiness[][][], int left, int i, int j){
        if (happiness[i][j][left] != Integer.MIN_VALUE) {
            return happiness[i][j][left];
        }

        if (i == 0 && j == 0) {
            happiness[i][j][left] = tree[i][j];
            return happiness[i][j][left];
        } else if (i > 0 && j == 0) {
            happiness[i][j][left] = tree[i][j];
            for (int k = i - 1; k > 0; k--) {
                if (tree[k][j] > 0) {
                    happiness[i][j][left] += tree[k][j];
                }
            }
            happiness[i][j][left] += tree[0][0];
            return happiness[i][j][left];
        } else if (i == 0 && j > 0) {
            happiness[i][j][left] = tree[i][j] - (left + 1) + maxHappiness(tree, happiness, left + 1, i, j - 1);
            return happiness[i][j][left];
        }
        int tmp1 = tree[i][j] - (left + 1) + maxHappiness(tree, happiness, left + 1, i, j - 1);
        int max = Integer.MIN_VALUE;
        for (int k = i - 1; k >= 0; k--) {
            max = java.lang.Math.max(max, tree[i][j] + maxHappiness(tree, happiness, 0, k, j));
        }

        happiness[i][j][left] = java.lang.Math.max(tmp1, max);

        return happiness[i][j][left];
    }

    public static void main(String[] args) {
        // this code reads the input data into a couple of lists
        Scanner reader = new Scanner(System.in);
        int w = reader.nextInt();
        int h = reader.nextInt();
        int[][] tree = new int[h][w];
        for (int j = 0; j < h; ++j) {
            for (int i = 0; i < w; ++i) {
                tree[j][i] = reader.nextInt();
            }
        }
        reader.close();

        // this will now be nested list
        // to get the value at coordinate (i, j) you would write tree[j][i]
        // i.e. row then column.
        // note that these are 0-indexed, not 1-indexed like the description.

        // TODO: implement your algorithm
        int answer = 0;
        int happiness[][][] = new int[h][w][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < w; k++) {
                    happiness[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        answer = maxHappiness(tree, happiness, 0, h - 1, w - 1);
        //showHappiness(happiness);
        // output a single number representing the solution
        // (your program should not output any other text)
        System.out.println(answer);
    }

}
