package com.qjx.repeatable.java.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程间传递数据 问题
 */
public class ThreadLocalTest {
    public static void main(String[] args) throws Exception{

        // 局限性一：父线程无法通过ThreadLocal向子线程传递线程私有数据
//        threadLocalTest01();
        // 解决方案 来了 使用 InheritableThreadLocal
//        InheritableThreadLocalTest01();

        //局限性二：父线程无法通过InheritableThreadLocal向池化的子线程(线程池)传递线程私有数据
//        InheritableThreadLocalTest02();

        //解决方案：使用transmittable-thread-local(alibaba)
//        myTtlTest();
        //使用代理类增强
//        myTtlTest02();

//        myTtlTest03();
        myTtlTest04();








    }

    private static void myTtlTest04() {
        ThreadLocal<Object> tl1 = new MyThreadLocal<>();
        tl1.set("111111");
        ThreadLocal<Object> tl2 = new MyThreadLocal<>();
        tl2.set("222222");
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService executorService = new MyExecutorService(Executors.newFixedThreadPool(1));
        executorService.execute(()->{
            System.out.println(tl1.get());
            System.out.println(tl2.get());
        });
        tl1.remove();
        tl2.remove();
        //--------------------
        tl1.set("33333333");
        tl2.set("44444444");
        executorService.execute(()->{
            System.out.println(tl1.get());
            System.out.println(tl2.get());
        });
        tl1.remove();
        tl2.remove();

    }

    private static void myTtlTest03() {

        ThreadLocal<Object> tl1 = new MyThreadLocal<>();
        tl1.set("111111");
        ThreadLocal<Object> tl2 = new MyThreadLocal<>();
        tl2.set("222222");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new MyTask(()->{
            System.out.println(tl1.get());
            System.out.println(tl2.get());
        }));
        tl1.remove();
        tl2.remove();
        //--------------------
        tl1.set("33333333");
        tl2.set("44444444");
        executorService.execute(new MyTask(()->{
            System.out.println(tl1.get());
            System.out.println(tl2.get());
        }));
        tl1.remove();
        tl2.remove();
    }

    private static void myTtlTest02() {
        ThreadLocal<Object> tl1 = new ThreadLocal<>();
        tl1.set("111111");
        ThreadLocal<Object> tl2 = new ThreadLocal<>();
        tl2.set("222222");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new MyTask(()->{

        },tl1,tl2));
        tl1.remove();
        tl2.remove();
        //--------------------
        tl1.set("33333333");
        tl2.set("44444444");
        executorService.execute(new MyTask(()->{

        },tl1,tl2));
        tl1.remove();
        tl2.remove();

    }

    private static void myTtlTest() {
        ThreadLocal<Object> tl1 = new ThreadLocal<>();
        tl1.set("111111");
        ThreadLocal<Object> tl2 = new ThreadLocal<>();
        tl2.set("222222");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new MyTask(tl1,tl2));
        tl1.remove();
        tl2.remove();
        //--------------------
        tl1.set("33333333");
        tl2.set("44444444");
        executorService.execute(new MyTask(tl1,tl2));
        tl1.remove();
        tl2.remove();
    }

    private static void InheritableThreadLocalTest02() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ThreadLocal<String> tl = new InheritableThreadLocal<>();
        tl.set("foo");
        executorService.execute(()->{
            String str = tl.get();
            //企图拿到父线程放进去的foo，成功了！！
            System.out.println(str);
        });
        //子线程 B 启动后，父线程A 休息 3秒， 保持子线程执行完毕
        TimeUnit.SECONDS.sleep(3);

        tl.remove();
        //--------------------------
        tl.set("bar");
        executorService.execute(()->{
            String str = tl.get();
            //企图拿到父线程放进去的foo，失败了！！
            System.out.println(str);
        });
        //子线程 B 启动后，父线程A 休息 3秒， 保持子线程执行完毕
        TimeUnit.SECONDS.sleep(3);

        tl.remove();

    }

    private static void InheritableThreadLocalTest01() throws InterruptedException {
        ThreadLocal<String> tl = new InheritableThreadLocal<>();
        tl.set("foo");

        Thread b = new Thread(()->{
            String str = tl.get();
            //这里企图拿到父线程放进去的 foo，然而成功了！！
            System.out.println(str);
        });

        b.start();

        //子线程 B 启动后，父线程A 休息 3秒， 保持子线程执行完毕
        TimeUnit.SECONDS.sleep(3);

        tl.remove();

    }

    private static void threadLocalTest01() throws InterruptedException {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("foo");

        Thread b = new Thread(()->{
            String str = tl.get();
            //这里企图拿到父线程放进去的 foo，然而失败了！！
            System.out.println(str);
        });

        b.start();

        //子线程 B 启动后，父线程A 休息 3秒， 保持子线程执行完毕
        TimeUnit.SECONDS.sleep(3);

        tl.remove();
    }
}
