package com.qjx.pattern.single;

public final class Single<T> {
    private static Object single;

    public Single(T t) {
        if (single != null) {
            throw new RuntimeException("重新初始化了...Single<" + t.getClass().getSimpleName() + ">");
        }
        single = t;
    }

    public T get() {
        return (T) single;
    }
}
