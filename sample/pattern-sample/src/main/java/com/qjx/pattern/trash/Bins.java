package com.qjx.pattern.trash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 14:03
 * @Description:
 * 一旦List<Trash> 装满了 Trash 对象，
 * Bins 构造器变回通过 instanceof 将该list分类到其类型化的垃圾箱中
 */
public class Bins {
    final List<Trash> bin;
    final List<Aluminum> aluminums = new ArrayList<>();
    final List<Paper> papers = new ArrayList<>();
    final List<Glass> glasses = new ArrayList<>();
    final List<Cardboard> cardboards = new ArrayList<>();

    public Bins(List<Trash> source) {
        bin = new ArrayList<>(source);//复制
        bin.forEach(t -> {
            if (t instanceof Aluminum) {
                aluminums.add((Aluminum) t);
            }
            if (t instanceof Paper) {
                papers.add((Paper) t);
            }
            if (t instanceof Glass) {
                glasses.add((Glass) t);
            }
            if (t instanceof Cardboard) {
                cardboards.add((Cardboard) t);
            }
        });
    }

    public void show() {
        TrashValue.sum(aluminums, "Aluminum");
        TrashValue.sum(papers, "Paper");
        TrashValue.sum(glasses, "Glass");
        TrashValue.sum(cardboards, "Cardboard");
        TrashValue.sum(bin, "Trash");
    }
}
