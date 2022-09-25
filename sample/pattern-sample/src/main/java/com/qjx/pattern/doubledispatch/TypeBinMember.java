package com.qjx.pattern.doubledispatch;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:19
 * @Description: 在不修改原有层次结构层次的情况下，
 * 将双路分发方法适配到trash的层次结构中
 */
public interface TypeBinMember {
    boolean addToBin(List<TypeBin> bins);
}
