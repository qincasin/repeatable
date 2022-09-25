package com.qjx.pattern.doubledispatch;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:25
 * @Description: 使用了双路分发的 Glass
 */
public class Glass extends com.qjx.pattern.trash.Glass implements TypeBinMember {
    public Glass(double weight) {
        super(weight);
    }

    @Override
    public boolean addToBin(List<TypeBin> bins) {
        return bins.stream().anyMatch(tb->tb.add(this));
    }
}
