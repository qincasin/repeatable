package com.qjx.pattern.trash;

import java.nio.file.FileVisitor;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:14
 * @Description:
 */
public abstract class Trash {
    public final double weight;

    public Trash(double weight) {
        this.weight = weight;
    }

    public abstract double price();

    @Override
    public String toString() {
        return String.format("%s wight: %.2f * price: %.2f = %.2f",getClass().getSimpleName(),weight,price(),weight*price());
    }

//    public abstract void accept(Visitor v);
}
