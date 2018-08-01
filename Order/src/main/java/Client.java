import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * main class for the application
 */
public class Client {

    public static final String STUDENT_NUMBER="99999999";

    public static void main(String[] args) {
        RobotMap finalMap = new RobotMap(args[0]);
        for(int i=1;i<args.length;i++) {
            RobotMap robotMap = new RobotMap(args[i]);
            finalMap = RobotMap.merge(finalMap, robotMap);
        }
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
        Date date = new Date();
        finalMap.writeToFile("combined-"+STUDENT_NUMBER+"-"+format.format(date)+".csv");
    }
}
