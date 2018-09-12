package common;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer,List<Integer>> edges= new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            if (edges.containsKey(start)){
                edges.get(start).add(end);
            }else {
                List<Integer> l = new ArrayList<>();
                l.add(end);
                edges.put(start,l);
            }
        }

        int count = 0;
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        visited.add(1);
        while (visited.size()!=n){
            int node = stack.peek();
            if (!edges.containsKey(node)){
                count++;
                stack.pop();
                continue;
            }
            boolean allVisited = true;
            for (int child:edges.get(node)) {
                if (visited.contains(child)){
                    stack.push(child);
                    visited.add(child);
                    count++;
                    allVisited=false;
                    break;
                }
            }
            if (allVisited){
                count++;
                stack.pop();
            }
        }
        System.out.println(count);
    }

}
