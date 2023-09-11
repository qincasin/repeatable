package com.qjx.sample.tool.learn.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Thread0调用了wait()方法后，会释放掉对象锁并暂停执行后续代码，即从wait()方法之后到run()方法结束的代码，都将立即暂停执行，这就是wait()方法在线程中的作用。
 * CPU会将对象锁分配给一直等候的Thread1线程，Thread1执行了notify()方法后，会通知其他正在等待线程（Thread0）得到锁，但会继续执行完自己锁内的代码之后，才会交出锁的控制权。
 * 因为本例只有两个线程，所以系统会在Thread1交出对象锁控制权后（Synchronized代码块中代码全部执行完后），把锁的控制权给Thread0（若还有其他线程，谁得到锁是随机的，完全看CPU心情），Thread0会接着wait()之后的代码，继续执行到Synchronized代码块结束，将对象锁的控制权交还给CPU。
 * <p>
 * Thread_0 =====Thread-0第一次获得锁，准备调用wait方法
 * Thread_1 =====Thread-1第一次获得锁，准备调用notify方法
 * Thread_1 =====线程Thread-1再次获得到了锁
 * Thread_0 =====线程Thread-0再次获得到了锁
 *
 * @author qinjiaxing on 2023/8/11
 * @author <others>
 */
public class WaitNotifyTest {

    public static Object object = new Object();

    public static void main(String[] args) {
        new Thread0().start();
        new Thread2().start();
        sleep(200);
        new Thread1().start();
    }

    private static void sleep(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Thread0 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("Thread_0 =====" + Thread.currentThread().getName() + "第一次获得锁，准备调用wait方法");
                try {
                    // 共享对象wait 方法，会让线程释放锁
                    // sleep(200);
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_0 =====" + "线程" + Thread.currentThread().getName() + "再次获得到了锁");
            }
        }
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("Thread_1 =====" + Thread.currentThread().getName() + "第一次获得锁，准备调用notify方法");
                // 线程共享对象，通过notify()方法，释放锁并通知其他线程可以得到锁
                object.notifyAll();
                //！这里注意，！ 打印的 Thread.currentThread().getName() 可能会存在并发问题，cpu时间片可能已经切到其他的线程中了，所以可能会打印的是其他线程的线程名字
                System.out.println("Thread_1 =====" + "线程" + Thread.currentThread().getName() + "再次获得到了锁");
            }
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("Thread_2 =====" + Thread.currentThread().getName() + "第一次获得锁，准备调用wait方法");
                try {
                    // 共享对象wait 方法，会让线程释放锁
                    // sleep(200);
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_2 =====" + "线程" + Thread.currentThread().getName() + "再次获得到了锁");
            }
        }
    }

}
