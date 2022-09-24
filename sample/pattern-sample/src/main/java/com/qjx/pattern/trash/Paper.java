package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:23
 * @Description:
 */
public class Paper extends Trash{

    public Paper(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.PAPER;
    }
}
