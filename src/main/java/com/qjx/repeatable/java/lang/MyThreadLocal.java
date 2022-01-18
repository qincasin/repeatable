package com.qjx.repeatable.java.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *  参考链接 https://www.jianshu.com/p/2b0c0ff40182
 *
 * @param <T>
 */
public class MyThreadLocal<T> extends ThreadLocal<T> {
    private static MyThreadLocal<HashSet<MyThreadLocal<Object>>> holder = new MyThreadLocal<HashSet<MyThreadLocal<Object>>>() {
        @Override
        protected HashSet<MyThreadLocal<Object>> initialValue() {
            return new HashSet<>();
        }
    };

    /**
     * 既然我们将ThreadLocal的引用保存在MyThreadLocal#holder这个静态变量中，那我们想办法暴露holder，不就可以得到capture需要的入参了吗？
     * 这种思路诚然是可行的，但从面向对象设计角度而言却不是最优的：
     * holder是MyThreadLocal的静态成员变量，维护的数据是ThreadLocal集合，它不应该将自身数据暴露出去，而是遵循高内聚的设计原则，提供数据操作的能力(方法)，
     * 例如提供capture的能力，但操作本身需要维护在类内部。因此应该是MyThreadLocal提供capture的能力，然后由需求方(MyTask)进行调用。代码如下
     */
    public static class DataTransmit{
        public static Map<ThreadLocal<Object>,Object> capture(){
            Map<ThreadLocal<Object>,Object> threadLocalValues = new HashMap<>();
            for (MyThreadLocal<Object> threadLocal : holder.get()) {
                threadLocalValues.put(threadLocal,threadLocal.get());
            }
            return threadLocalValues;
        }

        //还原
        public static void restore(Object obj) {
            Map<ThreadLocal<Object>,Object> backup = (Map<ThreadLocal<Object>,Object>)obj;
            for (Map.Entry<ThreadLocal<Object>, Object> entry : backup.entrySet()) {
                ThreadLocal<Object> threadLocal = entry.getKey();
                threadLocal.set(entry.getValue());
            }
        }

        //重放，用快照去覆盖当前线程的ThreadLocalMap
        public static Object replay(Object obj) {
            Map<ThreadLocal<Object>,Object> threadLocalValues = (Map<ThreadLocal<Object>,Object>)obj;
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

    /**
     * 重写set方法，在ThreadLocal#set方法执行时将ThreadLocal引用记录下来，保存在类成员变量holder中；
     * @param value
     */
    @Override
    public void set(T value) {
        super.set(value);
        addThisToHolder();
    }

    private void addThisToHolder() {
        if (!holder.get().contains(this)) {
            holder.get().add((MyThreadLocal<Object>) this);
        }
    }

    /**
     * 重写remove方法，在ThreadLocal#remove方法执行时将ThreadLocal引用一并移除
     */
    @Override
    public void remove() {
        super.remove();
        removeThisFromHolder();
    }

    private void removeThisFromHolder() {
        holder.get().remove(this);
    }
}
