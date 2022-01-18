package com.qjx.repeatable.java.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义Task，用于包装维护父线程ThreadLocalMap
 */
public class MyTask implements Runnable{
    private Runnable task;
    private final Map<ThreadLocal<Object>,Object> threadLocalValues;

    /**
     * 此时，我在MyThreadLocal中定义了一个内部类DataTransmit，
     * 用于ThreadLocal的数据传输，与MyThreadLocal本身提供的能力相隔离(SRP原则)。
     * 然后，将原先定义于MyTask的capture方法搬到了DataTransmit类内，提供capture的能力。此时，MyTask构造函数代码如下
     *
     * @param task
     */
    public MyTask(Runnable task) {
        this.task =task;
         threadLocalValues = MyThreadLocal.DataTransmit.capture();
    }

    public MyTask(Runnable task,ThreadLocal<Object> ... threadLocals) {
        this.task = task;
        this.threadLocalValues = new HashMap<>(threadLocals.length);
        //"拷贝"父线程TheadLocalMap的行为称为capture(拍照)
        //一个很生动形象的词：将父线程提交任务时刻的ThreadLocal值拍个快照并保存起来，后续使用
        capture(threadLocals);
    }


    public MyTask(ThreadLocal<Object> ... threadLocals) {
        this.threadLocalValues = new HashMap<>(threadLocals.length);
        //"拷贝"父线程TheadLocalMap的行为称为capture(拍照)
        //一个很生动形象的词：将父线程提交任务时刻的ThreadLocal值拍个快照并保存起来，后续使用
        capture(threadLocals);
    }

    private void capture(ThreadLocal<Object>[] threadLocals) {
        for (ThreadLocal<Object> threadLocal : threadLocals) {
            threadLocalValues.put(threadLocal,threadLocal.get());
        }
    }

    /**
     * 我们可以想像到，代码正执行到MyTask#run()方法，在该方法内部，能感知的上下文环境是正执行该方法的线程，以及MyTask维护的threadLocalValues(快照)，除此之外，它获取不了任何外界信息–> 这称之为线程封闭
     *
     * 因此可以比较自然地推理出，是要用MyTask的threadLocalValues(快照)去覆盖当前线程的ThreadLocalMap。我们称这个动作为replay(重放)
     *
     */
    @Override
    public void run() {
        Object backup = MyThreadLocal.DataTransmit.replay(threadLocalValues);
        try {
            task.run();
        }finally {
            MyThreadLocal.DataTransmit.replay(backup);
        }
//        Object backup = replay();
//        try {
//            //do biz
//            task.run();
////            doBiz();
//        }finally {
//            //还原
//            restore(backup);
//        }
    }

    private void doBiz() {
        Set<ThreadLocal<Object>> threadLocalSet = threadLocalValues.keySet();
        for (ThreadLocal<Object> threadLocal : threadLocalSet) {
            System.out.println(threadLocal.get());
        }
    }

    //还原
    private void restore(Object obj) {
        Map<ThreadLocal<Object>,Object> backup = (Map<ThreadLocal<Object>,Object>)obj;
        for (Map.Entry<ThreadLocal<Object>, Object> entry : backup.entrySet()) {
            ThreadLocal<Object> threadLocal = entry.getKey();
            threadLocal.set(entry.getValue());
        }
    }

    //重放，用快照去覆盖当前线程的ThreadLocalMap
    private Object replay() {
        Map<ThreadLocal<Object>,Object> backup = new HashMap<>();
        for (ThreadLocal<Object> threadLocal : threadLocalValues.keySet()) {
            backup.put(threadLocal,threadLocal.get());
        }

        for (Map.Entry<ThreadLocal<Object>, Object> entry : threadLocalValues.entrySet()) {
            ThreadLocal<Object> threadLocal = entry.getKey();
            threadLocal.set(entry.getValue());
        }
        return backup;
    }

}
