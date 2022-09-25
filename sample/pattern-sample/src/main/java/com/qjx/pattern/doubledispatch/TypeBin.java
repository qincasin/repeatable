package com.qjx.pattern.doubledispatch;

import com.qjx.pattern.trash.*;
import com.qjx.pattern.trash.Cardboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:20
 * @Description: 可以捕获到正确类型的List
 */
public class TypeBin {
    private List<Trash> typeBin = new ArrayList<>();
    public final String type;

    public TypeBin(String type) {
        this.type = type;
    }

    public List<Trash> bin() {
        //返回typeBin的副本
        return new ArrayList<>(typeBin);
    }

    protected boolean addIt(Trash t) {
        typeBin.add(t);
        return true;
    }

    public boolean add(Aluminum a) {
        return false;
    }

    public boolean add(Glass a) {
        return false;
    }

    public boolean add(Cardboard a) {
        return false;
    }

    public boolean add(Paper a) {
        return false;
    }
}

