import java.util.*;

public class Main {




//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//
//        int a[] = new int[n];
//        int t[] = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            t[i] = scanner.nextInt();
//        }
//
//        int total = 0;
//        for (int i = 0; i < n; i++) {
//            if (t[i]==1){
//                total+=a[i];
//            }
//        }
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            if(t[i]==0){
//                int m = 0;
//                for (int j = i; j < i+k&&j<n; j++) {
//                    if (t[j]==0){
//                        m+=a[j];
//                    }
//                }
//                max = Math.max(max,m);
//            }
//        }
//
//        System.out.println(total+max);
//    }


//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int a[] = new int[n];
//        TreeMap<Integer,Integer> map = new TreeMap<>();
//        int total = 0;
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//            total += a[i];
//            map.put(total,i+1);
//        }
//
//        int m = scanner.nextInt();
//        for (int i = 0; i < m; i++) {
//            int q = scanner.nextInt();
//            //小于等于q的最大键值
//            Map.Entry<Integer,Integer> entry1 = map.floorEntry(q);
//            //大于等于q的最小键值
//            Map.Entry<Integer,Integer> entry2 = map.ceilingEntry(q);
//            System.out.println(entry2.getValue());
//        }
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int k = scanner.nextInt();
//
//        String s="";
//        for (int i = 0; i < n; i++) {
//            s+="a";
//        }
//        for (int i = 0; i < m; i++) {
//            s+="z";
//        }
//
//        ArrayList<String> a = range(s);
//        System.out.println(a.get(k-1));
//
//    }
//
//    public static ArrayList<String> range(String str){
//        ArrayList<String> res = new ArrayList<>();
//        if(str.length()==0){
//            return res;
//        }
//        if(str.length()==1){
//            res.add(str);
//            return res;
//        }
//        for(int i=0;i<str.length();i++){
//            String p = "" + str.charAt(i);
//            ArrayList<String> subList = range(str.substring(0,i)+str.substring(i+1));
//            for(String sub:subList){
//                if(!res.contains(p+sub)){
//                    res.add(p+sub);
//                }
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n  = scanner.nextInt();
//
//        int a[] = new int[n];
//        int b[] = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            b[i] = scanner.nextInt();
//        }

//        int dp_max[][] = new int[n][n];
//        int dp_min[][] = new int[n][n];
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                if (i==j){
//                    dp_max[i][j] = a[i];
//                    dp_min[i][j] = b[i];
//                    if (dp_max[i][j]<dp_min[i][j]){
//                        count++;
//                    }
//                }else if(i<j){
//                    dp_max[i][j] = Math.max(dp_max[i][j-1],a[j]);
//                    dp_min[i][j] = Math.min(dp_min[i][j-1],b[j]);
//                    if (dp_max[i][j]<dp_min[i][j]){
//                        count++;
//                    }
//                }
//            }
//        }
//        System.out.println(count);

//        int dp_max[] = new int[n];
//        int dp_min[] = new int[n];
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                if (i==j){
//                    dp_max[j] = a[i];
//                    dp_min[j] = b[i];
//                    if (dp_max[j]<dp_min[j]){
//                        count++;
//                    }
//                }else if(i<j){
//                    dp_max[j] = Math.max(dp_max[j-1],a[j]);
//                    dp_min[j] = Math.min(dp_min[j-1],b[j]);
//                    if (dp_max[j]<dp_min[j]){
//                        count++;
//                    }
//                }
//            }
//        }
//        System.out.println(count);


          Scanner scanner = new Scanner(System.in);

          String line = scanner.nextLine();
          int M = Integer.valueOf(line.split(",")[0]);
          int N = Integer.valueOf(line.split(",")[1]);

          int seats[][] = new int[M][N];
          int flag[][] = new int[M][N];

        for (int i = 0; i < M; i++) {
            String tokens[] = scanner.nextLine().split(",");
            for (int j = 0; j < N; j++) {
                seats[i][j] = Integer.valueOf(tokens[j]);
            }
        }

        int count = 0;
        int max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (check(i, j,M,N,seats,flag)) {
                    int result = find(i, j, M, N, seats, flag);
                    count += 1;
                    if (result > max)
                        max = result;
                }
            }
        }

        System.out.println(count+","+max);

    }

    private static boolean check(int row, int col, int M, int N, int seats[][], int flag[][]){
        return row>=0&&row<M&&col>=0&&col<N&&flag[row][col]==0&&seats[row][col]==1;
    }

    private static int find(int row, int col, int M, int N, int seats[][], int flag[][]){
        int count = 0;
        if (check(row, col,M,N,seats,flag)) {
            flag[row][col] = 1;
            count += 1;
        }
        if (check(row-1, col-1,M,N,seats,flag)) {
            count += find(row - 1, col - 1,M,N,seats,flag);
        }
        if (check(row-1, col,M,N,seats,flag)) {
            count += find(row - 1, col,M,N,seats,flag);
        }
        if (check(row-1, col+1,M,N,seats,flag)) {
            count += find(row - 1, col + 1,M,N,seats,flag);
        }
        if (check(row, col-1,M,N,seats,flag)) {
            count += find(row, col - 1,M,N,seats,flag);
        }
        if (check(row, col,M,N,seats,flag)) {
            count += find(row, col,M,N,seats,flag);
        }
        if (check(row, col+1,M,N,seats,flag)) {
            count += find(row, col + 1,M,N,seats,flag);
        }
        if (check(row+1, col-1,M,N,seats,flag)) {
            count += find(row + 1, col - 1,M,N,seats,flag);
        }
        if (check(row+1, col,M,N,seats,flag)) {
            count += find(row + 1, col,M,N,seats,flag);
        }
        if (check(row+1, col+1,M,N,seats,flag)) {
            count += find(row + 1, col + 1,M,N,seats,flag);
        }

        return count;



    }

}
