package com.qjx.sample.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
 */
public class CountDownDemo {
    private int worker = 3;
    private CountDownLatch countDown = new CountDownLatch(worker);

    public void dThread() {
        new Thread(() -> {
            System.out.println("D开始运行...");
            try {
                System.out.println("D交出执行权限...");
                countDown.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("D又拿到了执行权限，执行结束...");
        }).start();

        Stream.of("A", "B", "C").forEach(t -> {
            new Thread(() -> {
                System.out.println(t + "抢到执行权限...,开始执行....");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t + "执行完毕...,交出执行权限....");

                countDown.countDown();
            }, t).start();
        });
    }

    public static void main(String[] args) {
        CountDownDemo demo = new CountDownDemo();
        demo.dThread();
    }


}
