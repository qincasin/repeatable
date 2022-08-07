package com.qjx.sample.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟 一件事情，A B C 三个任务，并且依次循环执行，类似于 A --> B --> C   ---> A --> B --> C --> A ..
 */
public class ReentrantLockDemo {
    private String format = "thread: %s 执行了任务";

    /**
     * 1:A
     * 2:B
     * 3:C
     */
    private static volatile int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();


    public void doA() {
        lock.lock();
        try {
            while (flag != 1) {
                conditionA.await();
            }

            System.out.println(print(Thread.currentThread().getName()));

            flag = 2;
            //唤醒B
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void doB() {
        lock.lock();
        try {
            while (flag != 2) {
                conditionB.await();
            }
            //执行B的真正的逻辑
            System.out.println(print(Thread.currentThread().getName()));
            flag = 3;
            //唤醒C
            conditionC.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void doC() {
        lock.lock();
        try {
            while (flag != 3) {
                conditionA.await();
            }
            //执行C的真正的逻辑
            System.out.println(print(Thread.currentThread().getName()));
            flag = 1;
            //唤醒A
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(demo::doA,"A").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(demo::doB,"B").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(demo::doC,"C").start();
        }
        System.out.println("11111");

    }

    private String print() {
        return String.format(format, Thread.currentThread().getName());
    }

    private String print(String threadName) {
        return String.format(format, threadName);
    }



}
