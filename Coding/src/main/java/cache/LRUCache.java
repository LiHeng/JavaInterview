package cache;

import java.util.HashMap;

public class LRUCache<K,V> {

    static class CacheNode<K,V>{
        K key;
        V value;
        long expireTime;
        CacheNode<K,V> pre;
        CacheNode<K,V> next;
    }

    private HashMap<K,CacheNode<K,V>> map;

    private CacheNode<K,V> head,tail;

    private int capacity;

    private int size;

    public LRUCache(int capacity){
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

        moveToHead(node);
        return node.value;
    }

    public V set(K key, V value){

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
            map.put(key,node);
            moveToHead(node);
            if (size<capacity){
                size++;
            }else {
                deleteFromTail();
            }
        }

        return null;
    }

    public V remove(K key) {
        CacheNode<K,V> node = map.get(key);
        if (node==null){
            return null;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
        return node.value;
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
        node.pre.next = node.next;
        node.next.pre = node.pre;

        //插入到头
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

}
