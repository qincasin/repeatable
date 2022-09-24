package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:23
 * @Description:
 */
public class Cardboard extends Trash{

    public Cardboard(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.CARDBOARD;
    }
}
