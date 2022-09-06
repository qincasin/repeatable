package com.qjx.pattern.single;

public interface Resource<T> {
    T get();

    void set(T t);
}
