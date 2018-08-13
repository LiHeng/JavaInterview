package cache;

import java.util.HashMap;

public class ExpiredLRUCache<K,V> {

    public static long NONE_EXPIRED = Long.MIN_VALUE;  //永不过期

    //private static long DEFAULT_DURATION = 60 * 1000 * 10;   //10min过期

    static class CacheNode<K,V>{
        K key;
        V value;
        long expiredTime;
        CacheNode<K,V> pre;
        CacheNode<K,V> next;
    }

    private HashMap<K,CacheNode<K,V>> map;

    private CacheNode<K,V> head,tail;

    private int capacity;

    private int size;

    public ExpiredLRUCache(int capacity){
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        head = new CacheNode<>();
        tail = new CacheNode<>();
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key){
        CacheNode<K,V> node = map.get(key);
        if (node==null){
            return null;
        }
        if (isCacheItemAlive(node)){
            moveToHead(node);
            return node.value;
        }else {                    // 缓存已过期,需要删除，惰性删除策略
            // remove cache item
            remove(node);
            size--;
            return null;
        }
    }

    public V set(K key, V value){
        return set(key,value,NONE_EXPIRED);
    }

    // duration 毫秒数
    public V set(K key, V value, long duration){

        if (map.containsKey(key)){
            CacheNode<K,V> node = map.get(key);
            V tmp = node.value;
            node.value = value;
            moveToHead(node);
            return tmp;
        }else {
            CacheNode<K,V> node = new CacheNode<>();
            node.key = key;
            node.value = value;
            if (duration!=NONE_EXPIRED) {
                node.expiredTime = duration + System.currentTimeMillis();    // 设置超时时间
            }else {
                node.expiredTime = NONE_EXPIRED;
            }
            map.put(key,node);
            moveToHead(node);
            if (size<capacity){
                size++;
            }else {
                deleteFromTail();    //删除尾部的节点
            }
        }
        return null;
    }

    private void remove( CacheNode<K,V> node) {
        if (node==null||node.next==null||node.pre==null)
            return;
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void deleteFromTail() {
        CacheNode<K,V> node = tail.pre;
        CacheNode<K,V> p = tail.pre.pre;
        p.next = tail;
        tail.pre = p;
        //删除map里面的键值对
        map.remove(node.key);
    }

    private void moveToHead(CacheNode<K, V> node) {
        if (node.pre==head){
            return;
        }
        // 取出node
        remove(node);

        //插入到头
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private boolean isCacheItemAlive(CacheNode<K, V> node) {
        return node.expiredTime==NONE_EXPIRED||System.currentTimeMillis() <= node.expiredTime;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiredLRUCache<String,Integer> cache = new ExpiredLRUCache<>(5);
        cache.set("1",1);
        cache.set("2",1);
        cache.set("3",1);
        cache.set("4",1);
        cache.set("5",1);
        cache.set("1",2);
        cache.set("6",3,2000);
        System.out.println(cache.get("2"));
        Thread.sleep(1500);
        System.out.println(cache.get("6"));
        Thread.sleep(700);
        System.out.println(cache.get("6"));
    }
}
