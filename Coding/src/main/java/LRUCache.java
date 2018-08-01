import java.util.LinkedHashMap;
import java.util.Map;

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
        LRUCache<Integer,String> cache = new LRUCache<>(5);
        cache.put(1,"str1");
        showCache(cache);
        cache.put(2,"str2");
        showCache(cache);
        cache.put(3,"str3");
        showCache(cache);
        cache.put(4,"str4");
        showCache(cache);
        cache.put(5,"str5");
        showCache(cache);

        cache.get(1);
        showCache(cache);

        cache.put(6,"str6");
        showCache(cache);
    }

    public static void showCache(LRUCache<Integer,String> cache){
        System.out.println("\nContent of cache\n");
        for (Map.Entry<Integer,String> entry:cache.entrySet()){
            System.out.println(entry.getValue());
        }
    }
}
