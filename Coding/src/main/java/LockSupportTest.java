import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    static Thread mainThread;

    public static void main(String[] args) {
        Thread threadA = new ThreadA("ta");
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" start ta");
        threadA.start();

        System.out.println(Thread.currentThread().getName()+" block");
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+ " continue!");
    }

    static class ThreadA extends Thread{
        public ThreadA(String name){
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" prepared to wakeup others!");
            try {
                sleep(2000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //LockSupport.unpark(mainThread);
            }
        }
    }

}
