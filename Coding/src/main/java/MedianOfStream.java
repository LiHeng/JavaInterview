import java.util.PriorityQueue;

public class MedianOfStream {

    private PriorityQueue<Integer> max_pq;

    private PriorityQueue<Integer> min_pq;

    public MedianOfStream(){
        max_pq = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
        min_pq = new PriorityQueue<>();
    }

    public void insert(int num){
        int size = max_pq.size()+min_pq.size();
        if (size%2==0){           // 如果size为偶数，插入元素到最小堆中
            if (max_pq.size()!=0&&max_pq.peek()>num){    // 如果要插入的值小于最大堆中堆顶元素
                max_pq.offer(num);
                num = max_pq.poll();
            }
            min_pq.offer(num);
        }else {                   // 如果size为奇数，插入元素到最大堆中
            if (min_pq.size()!=0&&min_pq.peek()<num){    // 如果要插入的值大于最小堆中堆顶元素
                min_pq.offer(num);
                num = min_pq.poll();
            }
            max_pq.offer(num);
        }
    }

    public int getMedian(){
        int size = max_pq.size()+min_pq.size();
        if (size==0){
            return -1;
        }

        int median = 0;
        if (size%2==0){
            median = (max_pq.peek()+min_pq.peek())/2;
        }else {
            median = min_pq.peek();
        }
        return median;
    }

    public static void main(String[] args) {
        MedianOfStream stream = new MedianOfStream();
        for (int i = 100; i >=50; i--) {
            stream.insert(i);
            System.out.println(stream.getMedian());
        }
    }
}
