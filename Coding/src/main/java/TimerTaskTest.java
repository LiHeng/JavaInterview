import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Task!!!");
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1*1000;
        timer.scheduleAtFixedRate(timerTask,delay,intervalPeriod);
    }

}
