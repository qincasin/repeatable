package com.qjx.pattern.doubledispatch;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:25
 * @Description: 使用了双路分发的 Aluminum
 */
public class Aluminum extends com.qjx.pattern.trash.Aluminum implements TypeBinMember {
    public Aluminum(double weight) {
        super(weight);
    }

    @Override
    public boolean addToBin(List<TypeBin> bins) {
        return bins.stream().anyMatch(tb -> tb.add(this));
    }
}
