import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class CollectionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Stack<Integer> stack = new Stack<>();
        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, String> hashMap = new HashMap<>();
        TreeSet<String> treeSet = new TreeSet<>();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        LinkedList<String> linkedList = new LinkedList<>();
        Arrays.sort(new int[]{});
        Collections.sort(linkedList);

        ReentrantLock lock = new ReentrantLock();

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        //Collections.sort(null);
        //Arrays.sort(new Object[2]);
        hashMap.put("lihang", "ChongQing");
        ThreadLocal<Connection> connections = new ThreadLocal<>();

        FutureTask<String> task = new FutureTask<>((Callable<String>) () -> {
            Thread.sleep(1000);
            return null;
        });

        Collections.synchronizedList(new ArrayList<>());

        Class.forName("mysql.Driver");

        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
//                    for (;;){
//
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setName("Thread-test");
        thread.start();

        LockSupport.park();
    }
}
