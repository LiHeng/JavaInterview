import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 利用LinkedHashMap 实现一个 LRU 缓存
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    public LRUCache(int capacity){
        super(16,0.75f,true);
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.valueOf(scanner.nextLine());
        LRUCache<Integer,Integer> cache = new LRUCache<>(capacity);
        while(scanner.hasNextLine()){
            String[] tokens = scanner.nextLine().split(" ");
            if (tokens[0].equals("p")){
                cache.put(Integer.valueOf(tokens[1]),Integer.valueOf(tokens[2]));
            }else{
                Integer v = cache.get(Integer.valueOf(tokens[1]));
                System.out.println(v==null?-1:v);
            }
        }
    }


}
