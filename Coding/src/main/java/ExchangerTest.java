import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExchangerTest {

    static class Car extends Thread {
        private Exchanger<String> exchanger;

        public Car(Exchanger<String> exchanger) {
            super("Thread-Car");
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + exchanger.exchange("Car"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Bike extends Thread {
        private Exchanger<String> exchanger;

        public Bike(Exchanger<String> exchanger) {
            super("Thread-Bike");
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + ": " + exchanger.exchange("Bike"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable {

        /**
         * 生产者和消费者进行交换的数据结构
         */
        private List<String> buffer;


        /**
         * 同步生产者和消费者的交换对象
         */
        private final Exchanger<List<String>> exchanger;


        Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            int cycle = 1;
            for (int i = 0; i < 10; i++) {
                System.out.println("Producer : Cycle :" + cycle);
                for (int j = 0; j < 10; j++) {
                    String message = "Event " + ((i * 10) + j);
                    System.out.println("Producer : " + message);
                    buffer.add(message);
                }

                //调用exchange()与消费者进行数据交换
                try {
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer :" + buffer.size());
                cycle++;
            }
        }
    }

    static class Consumer implements Runnable{
        private List<String> buffer;

        private final Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer,Exchanger<List<String>> exchanger){
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            int cycle = 1;
            for(int i = 0 ; i < 10 ; i++){
                System.out.println("Consumer : Cycle :" + cycle);

                //调用exchange()与消费者进行数据交换
                try {
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer :" + buffer.size());
                for(int j = 0 ; j < 10 ; j++){
                    System.out.println("Consumer : " + buffer.get(0));
                    buffer.remove(0);
                }
                cycle++ ;
            }
        }
    }

    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2, exchanger);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }


}
