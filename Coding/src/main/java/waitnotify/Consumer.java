package waitnotify;

import java.util.Date;
import java.util.Queue;

public class Consumer implements Runnable {

    private Queue<Date> data;

    public Consumer(Queue<Date> data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            synchronized (data){
                while (data.isEmpty()){
                    try {
                        System.out.println("队列为空,等待生产者生产!");
                        data.wait();
                    } catch (InterruptedException e) {
                        data.notify();
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" "+data.poll());
                data.notify();
            }
        }
    }
}
