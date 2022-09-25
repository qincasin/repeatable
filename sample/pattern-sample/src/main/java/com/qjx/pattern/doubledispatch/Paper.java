package com.qjx.pattern.doubledispatch;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:27
 * @Description: 使用了双路分发的 Paper
 */
public class Paper extends com.qjx.pattern.trash.Paper implements TypeBinMember {
    public Paper(double weight) {
        super(weight);
    }

    @Override
    public boolean addToBin(List<TypeBin> bins) {
        return bins.stream().anyMatch(tb->tb.add(this));
    }
}
