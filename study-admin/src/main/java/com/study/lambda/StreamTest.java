package com.study.lambda;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream.of("1","2","3").forEach(t->{
            if (t.equals("2")) {
                return;
            }
            System.out.println(t);
        });
        boolean b = Stream.of("1", "2", "3").anyMatch(t -> {
            System.out.println(t);
            if (t.equals("3")) {
                return true;
            } else {
                return false;
            }
        });
        System.out.println(b);

        Integer count = Stream.of("1", "2", "3").mapToInt(i->{
                    return Integer.parseInt(i);
                })
                .map(t -> {
                    System.out.println(t);
                    return t==2 ? 1 : 0;
                })
                .reduce(0, Integer::sum);

        System.out.println(count);

        Object o = Optional.ofNullable("1").orElse("空");
        System.out.println(o);
    }
}
