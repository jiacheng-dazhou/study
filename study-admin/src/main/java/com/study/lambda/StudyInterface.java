package com.study.lambda;

public interface StudyInterface {
    int i = 10;
    void test();
    default void testDefault(){
        System.out.println("默认方法...");
    }
    static void testStatic(){
        System.out.println("静态方法...");
    }

    class A implements StudyInterface{
        @Override
        public void test() {

        }

    }
}
