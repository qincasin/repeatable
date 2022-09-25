package com.qjx.pattern.trash;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 09:47
 * @Description: 使List 具备 Fillable能力的适配器
 */
public class FillableList<T extends Trash> implements Fillable<T> {
    private List<T> list;

    public FillableList(List<T> list) {
        this.list = list;
    }

    @Override
    public void addTrash(T t) {
        list.add(t);
    }
}
