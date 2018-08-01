package waitnotify;

import java.io.UncheckedIOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable,Cloneable{
    private Queue<Date> data;


    private int queueSize;

    public Producer(Queue<Date> data,int queueSize){
        this.data  = data;
        this.queueSize = queueSize;
    }


    @Override
    public void run() {
        while (true){
            synchronized (data){
                while (data.size()>=queueSize){
                    try {
                        System.out.println("队列已满!等待消费者消费...");
                        data.wait();
                    } catch (InterruptedException e) {
                        data.notify();
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.offer(new Date());
                data.notifyAll();
                System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-data.size()));
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
