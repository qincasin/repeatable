package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:23
 * @Description:
 */
public class Aluminum extends Trash{

    public Aluminum(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.ALUMINUM;
    }
}
