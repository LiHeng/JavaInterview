package guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;

import java.util.Collection;

public class GuavaTest {

    public static void main(String[] args) {
        Multimap<String,String> map = MultimapBuilder.hashKeys().linkedListValues().build();
        map.put("a","1");
        map.put("a","2");
        Collection<String> c = map.get("a");
        c.forEach(System.out::println);
    }

}
