package niuke;

import java.util.Scanner;

public class StringUtil {

    public static int compare(String target, String search){
        int max = java.lang.Math.min(target.length(),search.length());
        for (int i = 0; i < max; i++) {
            if (target.charAt(i)==search.charAt(i)){
                continue;
            }else if (target.charAt(i)>search.charAt(i)){
                return 1;
            }else {
                return -1;
            }
        }
        return 0;
    }

    public static int binarySearch(String target, String[] toSearch){
        int low = 0;
        int high = toSearch.length-1;

        while (low<=high){
            int mid = low+(high-low)/2;
            if (compare(target, toSearch[mid])<0){
                high = mid-1;
            }else if(compare(target, toSearch[mid])==0){
                return 1;
            }else {
                low = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int m = Integer.valueOf(line.split(" ")[0]);
            int n = Integer.valueOf(line.split(" ")[1]);

            String toSearch[] = new String[m];
            for (int i = 0; i < m; i++) {
                toSearch[i] = scanner.nextLine();
            }

            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                String t = scanner.nextLine();
                System.out.println(binarySearch(t, toSearch));
            }
            if (scanner.hasNext()){
                scanner.nextLine();
                System.out.println();
            }
        }
    }

}
