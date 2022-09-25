package com.qjx.pattern.doubledispatch;

import com.qjx.pattern.trash.Cardboard;
import com.qjx.pattern.trash.ParseTrash;
import com.qjx.pattern.trash.Trash;
import com.qjx.pattern.trash.TrashValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 11:30
 * @Description: 再一次方法调用中，用多路分发处理多个未知类型
 */
class AluminumBin extends TypeBin {

    public AluminumBin() {
        super("Aluminum");
    }

    @Override
    public boolean add(Aluminum a) {
        return addIt(a);
    }
}

class GlassBin extends TypeBin {
    public GlassBin() {
        super("Glass");
    }

    @Override
    public boolean add(Glass a) {
        return addIt(a);
    }
}

class PaperBin extends TypeBin {
    public PaperBin() {
        super("Paper");
    }

    @Override
    public boolean add(Paper a) {
        return addIt(a);
    }
}

class CardboardBin extends TypeBin {
    public CardboardBin() {
        super("Carboard");
    }

    @Override
    public boolean add(Cardboard a) {
        return addIt(a);
    }
}

/**
 * TrashBinSet 封装了所有不同类型的TypeBin，以及用于启动双路分发的  sortIntoBins() 方法，一旦构架好了结构，分类到不同的TypeBin中就是很简单的事情了
 */
class TrashBinSet {
    public final List<TypeBin> binSet = Arrays.asList(new AluminumBin(), new PaperBin(), new GlassBin(),
            new CardboardBin());

    @SuppressWarnings("unchecked")
    public void sortIntoBins(List bin) {
        bin.forEach(aBin -> {
            TypeBinMember t = (TypeBinMember) aBin;
            if (!t.addToBin(binSet)) {
                throw new RuntimeException("sortIntoBins() cloud not add " + t);
            }
        });
    }
}


public class DoubleDispatch {

    /**
     *
     */
    public static void main(String[] args) {
        List<Trash> bin = new ArrayList<>();
        ParseTrash.fillBin("doubledispatch", bin);
        TrashBinSet bins = new TrashBinSet();
        //将主bin中分类到单个类型的bin中
        bins.sortIntoBins(bin);
        //累加每个bin的值
        for (TypeBin typeBin : bins.binSet) {
            TrashValue.sum(typeBin.bin(), typeBin.type);
        }
        //累加主bin的值
        TrashValue.sum(bin, "Trash");

    }
}
