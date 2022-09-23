package com.study.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaStudy {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);

        Collections.sort(list,(Integer str1,Integer str2)->{
            return str2-str1;
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        StudyInterface.A a = new StudyInterface.A();
        a.testDefault();
    }
}
