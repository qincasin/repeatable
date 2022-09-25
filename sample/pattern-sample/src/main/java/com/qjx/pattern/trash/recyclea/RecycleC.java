package com.qjx.pattern.trash.recyclea;

import com.qjx.pattern.trash.Aluminum;
import com.qjx.pattern.trash.Cardboard;
import com.qjx.pattern.trash.Glass;
import com.qjx.pattern.trash.Paper;
import com.qjx.pattern.trash.ParseTrash;
import com.qjx.pattern.trash.Trash;
import com.qjx.pattern.trash.TrashValue;

import java.util.ArrayList;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 10:32
 * @Description: 设计原则：用数据成员处理状态的变化，用多态处理行为的变化
 */


class TrashBin<T extends Trash> extends ArrayList<T> {
    final Class<T> binType;

    public TrashBin(Class<T> binType) {
        this.binType = binType;
    }

    @SuppressWarnings("unchecked")
    boolean grab(Trash t) {
        //比较的类型
        if (t.getClass().equals(binType)) {
            add((T) t); //向下转型为这个TrashBin 类型
            return true;  //捕获到了 Trash
        }
        return false; //未捕获到 Trash
    }
}

/**
 * TrashBinList 包含了一个TrashBin 引用组成的List，因此sort() 在为每个Trash对象查找匹配项时，可以遍历TrashBin
 */
class TrashBinList<T extends Trash> extends ArrayList<TrashBin<? extends T>> {
    public TrashBinList(Class<? extends T>... types) {
        for (Class<? extends T> type : types) {
            add(new TrashBin<>(type));
        }
    }

    public boolean sort(T t) {
        for (TrashBin<? extends T> ts : this) {
            if (ts.grab(t)) {
                return true;
            }
        }
        return false;//未找到t的trashBin
    }

    /**
     * sortBin 从他的TrashBin参数中选出每一块Trash，并将其分类到相应的具体TrashBin中
     *
     * @param bin
     */
    public void sortBin(TrashBin<T> bin) {
        for (T trash : bin) {
            if (!sort(trash)) {
                throw new RuntimeException("bin not found for " + trash);
            }
        }
    }

    public void show(){
        for (TrashBin<? extends T> ts : this) {
            String simpleName = ts.binType.getSimpleName();
            TrashValue.sum(ts,simpleName);
        }
    }
}


public class RecycleC {
    public static void main(String[] args) {
        TrashBin<Trash> bin = new TrashBin<>(Trash.class);
        ParseTrash.fillBin("trash",bin);
        @SuppressWarnings("unchecked")
        TrashBinList<Trash> trashBins = new TrashBinList<>(Aluminum.class, Paper.class, Glass.class, Cardboard.class);
        trashBins.sortBin(bin);  //一次方法调用回触发操作，将bin的内部分别放到各自特定类型的垃圾箱(bin)中
        trashBins.show();
        TrashValue.sum(bin,"Trash");
    }
}
