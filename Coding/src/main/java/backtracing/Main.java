package backtracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        int x[] = new int[n+1];
        int y[] = new int[n+1];
        x[0] = 0;
        y[0] = 0;
        for (int i = 1; i <= n; i++) {
            String[] coordinate = scanner.nextLine().split(",");
            x[i] = Integer.valueOf(coordinate[0]);
            y[i] = Integer.valueOf(coordinate[1]);
        }

        int num = x.length-1;
        int[][] dist = new int[num + 1][num+1];
        //所有点两两之间的距离
        for (int i = 0; i < num + 1; i++) {
            for (int j = 0; j < num+1; j++) {
                dist[i][j] = Math.abs(x[j] - x[i]) + Math.abs(y[j] - y[i]);
            }
        }
        List<Integer> list = new ArrayList<>(num);
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        List<List<Integer>> allPaths = getCombinations(list);

        for(List<Integer> l:allPaths){
            System.out.println(l);
        }

        int min = Integer.MAX_VALUE;
        for(List<Integer> path : allPaths){
            int pathLength = 0;

            int fromNode = 0;
            int toNode;

            for (int i = 0; i < num; i++) {
                toNode = path.get(i);
                pathLength += dist[fromNode][toNode];
                fromNode = toNode;
            }

            pathLength += dist[fromNode][0];
            min = Math.min(min,pathLength);
        }
        System.out.println(min);
    }

    static  List<List<Integer>> getCombinations(List<Integer> integers) {
        if (integers.size() == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Integer firstElement = integers.remove(0);
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> permutations = getCombinations(integers);
        for (List<Integer> subList : permutations) {
            for (int index=0; index <= subList.size(); index++) {
                List<Integer> temp = new ArrayList<>(subList);
                temp.add(index, firstElement);
                res.add(temp);
            }
        }
        return res;
    }
}
