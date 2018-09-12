import java.util.*;


public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.valueOf(scanner.nextLine());
        int goodsA[] = new int[N];
        int goodsB[] = new int[N];
        int goodsC[] = new int[N];

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String tokens[] = line.split(" ");
            goodsA[i] = Integer.valueOf(tokens[0]);
            goodsB[i] = Integer.valueOf(tokens[1]);
            goodsC[i] = Integer.valueOf(tokens[2]);
        }

        answer(goodsA,goodsB,goodsC,N);
    }

    private static void answer(int goodsA[],int goodsB[],int goodsC[],int n){
        Set<Integer> results = new HashSet<>();
        for (int m = 0; m < n; m++) {
            for (int k = 0; k < m; k++) {
                if (goodsA[m] > goodsA[k] && goodsB[m] > goodsB[k] && goodsC[m] > goodsC[k]) {
                    results.add(k);
                }
                if (goodsA[m] < goodsA[k] && goodsB[m] < goodsB[k] && goodsC[m] < goodsC[k]) {
                    results.add(m);
                }
            }
        }

        System.out.println(results.size());
    }


}
