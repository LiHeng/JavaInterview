package java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class Lambda {

    static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        List<Apple> apples = new ArrayList<>();

        // List.sort 为默认方法
        users.sort(Comparator.comparing(User::getAge).reversed());

        filterApples(apples,Apple::isGreenApple);

        //匿名函数
        filterApples(apples,apple->"green".equals(apple.getColor()));

        //流式api 顺序处理
        apples = apples.stream().filter((Apple apple)->"green".equals(apple.getColor()))
                .collect(toList());

        //流式api 并行处理
        apples = apples.parallelStream().filter((Apple apple)->"green".equals(apple.getColor()))
                .collect(toList());

        // Consumer 接口
        apples.forEach((Apple apple)->apple.setColor(""));

        // 得到所有苹果的颜色
        List<String> a = apples.stream().map(Apple::getColor).collect(toList());

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparing(String::length).thenComparing(String::toString));



    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Apple {
        private int weight = 0;
        private String color = "";

        public static boolean isGreenApple(Apple apple) {
            return "green".equals(apple.getColor());
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
