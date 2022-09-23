package com.study.user.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void main(String[] args) {
        /*final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1");
            }
        });

        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //等待线程t1执行完成后
                    //本线程t2 再执行
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //等待线程t2执行完成后
                    //本线程t3 再执行
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程3");
            }
        });
        t3.start();
        t2.start();
        t1.start();*/

        /*CountDownLatch countDownLatch1 = new CountDownLatch(0);

        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        CountDownLatch countDownLatch3 = new CountDownLatch(1);


        Thread t1 = new Thread(new Work(countDownLatch1, countDownLatch2),"线程1");
        Thread t2 = new Thread(new Work(countDownLatch2, countDownLatch3),"线程2");
        Thread t3 = new Thread(new Work(countDownLatch3, countDownLatch3),"线程3");

        t1.start();
        t2.start();
        t3.start();
    }

    static class Work implements Runnable {
        CountDownLatch cOne;
        CountDownLatch cTwo;

        public Work(CountDownLatch cOne, CountDownLatch cTwo) {
            super();
            this.cOne = cOne;
            this.cTwo = cTwo;
        }

        @Override
        public void run() {
            try {
                cOne.await();
                System.out.println("执行: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                cTwo.countDown();
            }
        }*/

        Thread thread1 = new Thread(new Work(), "线程1");
        Thread thread2 = new Thread(new Work(), "线程2");
        Thread thread3 = new Thread(new Work(), "线程3");

        CompletableFuture.runAsync(()->{thread1.start();}).thenRun(()->thread2.start()).thenRun(()->thread3.start());

        CountDownLatch count1 = new CountDownLatch(1);
        CountDownLatch count2 = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1");
                count1.countDown();
            }
        },"线程1");

        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                count1.await();
                System.out.println("线程2");
                count2.countDown();
            }
        },"线程2");

        Thread t3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                count2.await();
                System.out.println("线程3");
            }
        });

       /* ExecutorService executor = Executors.newSingleThreadExecutor();
        // 将线程依次加入到线程池中
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        // 及时将线程池关闭
        executor.shutdown();*/

        t3.start();
        t2.start();
        t1.start();

    }
    static class Work implements Runnable{
        @Override
        public void run() {
            System.out.println("当前："+Thread.currentThread().getName()+"正在执行...");
        }
    }
}
