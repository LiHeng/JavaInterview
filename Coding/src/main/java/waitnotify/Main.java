package waitnotify;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception{
        Queue<Date> data=new LinkedList<>();
        Producer p = new Producer(data,10);
        p.clone();
        Thread producer = new Thread(p);

        Consumer consumer = new Consumer(data);
        producer.start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

}
