package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:23
 * @Description:
 */
public class Glass extends Trash{

    public Glass(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.GLASS;
    }
}
