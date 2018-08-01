package threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class ThreadPoolTest {

    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service=Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            service.execute(new Task());
        }
        service.shutdown();
    }
}
