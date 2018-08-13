import java.util.Scanner;
import java.util.Stack;

public class A5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int ss[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            ss[i][0] = scanner.nextInt();
            ss[i][1] = scanner.nextInt();
            if (ss[i][0]>ss[i][1]){
                ss[i][1] = ss[i][1]+m;
            }
        }
        int count =0 ;
        int start = 0;

        while (ss.length>0) {
            start = find(start,ss);
            if(start!=-1){
                count+=1;
            }else {
                break;
            }
        }

        System.out.println(count);


    }

    private static int find(int start, int ss[][]){
        int min = ss[0].length+1;
        for (int i = 0; i < ss.length; i++) {
            if(ss[i][0]>=start&&ss[i][1]<min){
                min = ss[i][1];
            }
        }
        if (min<ss[0].length+1){
            return min;
        }
        return -1;
    }
}
