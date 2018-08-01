package niuke;

import java.util.*;

public class UserPreference {

    static class Node implements Comparable<Node>{
        int preferences;
        int index;

        public Node(int preferences, int index) {
            this.preferences = preferences;
            this.index = index;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(preferences,o.preferences);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Node> nodes = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            nodes.add(new Node(val, i+1));
        }

        Collections.sort(nodes);

        for (int i = 0; i < nodes.size(); i++) {
            if (!map.containsKey(nodes.get(i).preferences)){
                map.put(nodes.get(i).preferences, i);
            }
        }

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int k = scanner.nextInt();
            Integer start = map.get(k);
            if (start==null){
                System.out.println(0);
            }else {
                int count = 0;
                for (int j = start; j < nodes.size(); j++) {
                    if (nodes.get(j).preferences == k) {
                        if(nodes.get(j).index>=l&&nodes.get(j).index<=r)
                            count++;
                    }else{
                        break;
                    }
                }
                System.out.println(count);
            }
        }
    }

}
