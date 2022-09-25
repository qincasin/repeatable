package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 09:46
 * @Description: 支持调用addTrash（）方法的一个接口
 */
public interface Fillable<T> {
    void addTrash(T t);

}
