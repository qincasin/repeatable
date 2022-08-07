package com.qjx.sample.juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber {

    private static AtomicInteger count = new AtomicInteger();
    //true 代表偶数，false 代表奇数
    private boolean flag = false;

    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        //使用 线程池 +  AtomicInteger 实现 奇偶数打印
        //        printNumber.printNumber1();
        // 使用ReentrantLock与Condition 交替打印奇偶数
        //        printNumber.printNumber2();
        //仅仅用 volatile 关键字
        printNumber.printNumber3();
    }


    /**
     * 使用 线程池 +  AtomicInteger 实现 奇偶数打印
     */
    public void printNumber1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count.get() < 100) {
                if (flag) {
                    System.out.println("偶数" + count.incrementAndGet());
                    flag = false;
                }
            }
        });

        executorService.execute(() -> {
            while (count.get() < 100) {
                if (!flag) {
                    System.out.println("奇数" + count.incrementAndGet());
                    flag = true;
                }
            }
        });
        executorService.shutdown();
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    private int count2 = 0;

    /**
     * 使用ReentrantLock与Condition 交替打印奇偶数
     */
    public void printNumber2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count2 < 100) {
                try {
                    lock.lock();
                    System.out.println("偶数：" + count2);
                    count2++;
                    //偶数线程阻塞
                    condition1.await();
                    //奇数线程唤醒
                    condition2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        executorService.execute(() -> {
            while (count2 < 100) {
                try {
                    lock.lock();
                    System.out.println("奇数：" + count2);
                    count2++;
                    //偶数线程 唤醒
                    condition1.signal();
                    //奇数线程阻塞
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        executorService.shutdown();

    }


    //count 不需要是volatile类型的。
    //因为flag 是volatile的，线程1 对 flag写 之前的变量（即count），对后续 读flag的线程2 立即可见。
    private static int count3 = 0;
    private static volatile boolean flag3 = false;

    /**
     * 仅仅用 volatile 关键字
     */
    public void printNumber3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count3 < 100) {
                if (flag) {
                    System.out.println(count3);
                    count3++;
                }
            }
        });

        executorService.execute(() -> {
            while (count3 < 100) {
                if (!flag) {
                    System.out.println(count3);
                    count3++;
                }
            }
        });
        executorService.shutdown();
    }
}
