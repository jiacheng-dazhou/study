package com.study.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SupplierStudy {
    public static void main(String[] args) {
        getSum(() -> {
            int arr[] = {1, 2, 3, 4, 5};
            return arr[arr.length - 1];
        });

        consumerTest(msg1->{
            System.out.print(msg1);
        },msg2->{
            System.out.println(msg2);
        });

        Integer i = functionTest(msg1 -> {
            return msg1 * 10;
        }, msg2 -> {
            return msg2 + 20;
        },1);
        System.out.println(i);

        predicateTest(msg->{
            return i*100>100?true:false;
        },10);
    }

    public static void getSum(Supplier<Integer> supplier) {
        Integer i = supplier.get();
        System.out.println("max=" + i);
    }

    public static void consumerTest(Consumer consumer1,Consumer consumer2) {
        consumer1.accept("hello ");
        consumer2.accept("world");
//        consumer2.andThen(consumer1).accept("hello world");
    }

    public static Integer functionTest(Function<Integer,Integer> f1,Function<Integer,Integer> f2,Integer i){
        return f1.andThen(f2).apply(i);
    }

    public static void predicateTest(Predicate<Integer> p,Integer i){
        System.out.println(p.test(i));
    }
}
