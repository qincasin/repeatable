package com.qjx.pattern.trash.recyclea;

import com.qjx.pattern.trash.Bins;
import com.qjx.pattern.trash.ParseTrash;
import com.qjx.pattern.trash.Trash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 10:14
 * @Description: 使用ParseTrash.fillBin()
 */
public class RecycleB {
    public static void main(String[] args) {
        List<Trash> bin = new ArrayList<>();
        ParseTrash.fillBin("trash",bin);
        Bins bins = new Bins(bin);
        bins.show();
    }
}
