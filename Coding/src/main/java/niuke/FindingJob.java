package niuke;

import java.util.*;


public class FindingJob {

    static class Job implements Comparable<Job>{
        int difficult;
        int payment;     // max payment can get

        public Job(int difficult, int payment) {
            this.difficult = difficult;
            this.payment = payment;
        }

        @Override
        public boolean equals(Object obj) {
            if(! (obj instanceof Job))
                return false;
            Job other = (Job)obj;
            return difficult==other.difficult&&payment == other.payment;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(difficult,o.difficult);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<Job> jobs = new ArrayList<>();
        int ability[] = new int[M];

        for (int i = 0; i < N; i++) {
            jobs.add(new Job(scanner.nextInt(), scanner.nextInt()));
        }
        for (int i = 0; i < M; i++) {
            ability[i] = scanner.nextInt();
        }

        Collections.sort(jobs);


        for (int i=1;i<jobs.size();i++){
            jobs.get(i).payment = java.lang.Math.max(jobs.get(i-1).payment,jobs.get(i).payment);
        }

        TreeMap<Integer, Integer>  map= new TreeMap<>();
        for (Job job : jobs) {
            map.put(job.difficult,job.payment);
        }

        for (int abl : ability) {
            Integer p = map.floorKey(abl);
            if (p!=null)
                System.out.println(map.get(p));
            else
                System.out.println(0);
        }
    }

}
