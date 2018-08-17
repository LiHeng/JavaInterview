import java.util.concurrent.atomic.AtomicReference;

public class SparseMatrix {

    //稀疏矩阵乘法优化
    public static int[][] multiply(int[][] matrixA, int[][] matrixB){
        int[][] res = new int[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB.length; j++) {
                if (matrixA[i][j]!=0){            //当A矩阵中的元素不为0时才去运算
                    for (int k = 0; k < matrixB[0].length; k++) {
                        res[i][k] += matrixA[i][j]*matrixB[j][k];
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                new int[]{0,0,0,1},
                new int[]{0,0,0,2},
                new int[]{1,0,0,3},
        };
        int[][] b = new int[][]{
                new int[]{0,0,0,1,0},
                new int[]{0,0,0,2,0},
                new int[]{0,0,0,0,0},
                new int[]{4,2,5,0,0},
        };

        int[][] c=multiply(a,b);
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                System.out.print(c[i][j]+" ");
            }
            System.out.println();
        }
    }
}
