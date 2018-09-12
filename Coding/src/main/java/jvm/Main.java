package jvm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int board[][] = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==1){
                    dfs(board,M,i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void dfs(int board[][], int M, int i, int j){
        if (board[i][j]==0||board[i][j]==-1){
            return;
        }

        board[i][j] = -1;
        if (isValid(M,i+1,j)) {
            dfs(board, M, i + 1, j);
        }
        if (isValid(M,i-1,j)) {
            dfs(board, M, i - 1, j);
        }
        if (isValid(M,i,j+1)) {
            dfs(board, M, i , j+1);
        }
        if (isValid(M,i,j-1)) {
            dfs(board, M, i , j-1);
        }
    }

    private static boolean isValid(int M,int i,int j){
        return i>=0&&i<M&&j>=0&&j<M;
    }

}
