package com.qjx.pattern.doubledispatch;


import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:27
 * @Description: 使用了双路分发的 Cardboard
 */
public class Cardboard extends com.qjx.pattern.trash.Cardboard implements TypeBinMember {
    public Cardboard(double weight) {
        super(weight);
    }

    @Override
    public boolean addToBin(List<TypeBin> bins) {
        return bins.stream().anyMatch(tb -> tb.add(this));
    }
}
