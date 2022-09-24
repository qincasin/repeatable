package com.qjx.pattern.trash.recyclea;

import com.qjx.pattern.trash.Aluminum;
import com.qjx.pattern.trash.Bins;
import com.qjx.pattern.trash.Glass;
import com.qjx.pattern.trash.Paper;
import com.qjx.pattern.trash.Trash;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 14:08
 * @Description: 用反射实现的垃圾收集
 */

class SimpleFactory {
    static final List<Function<Double, Trash>> constructors = Arrays.asList(Aluminum::new, Paper::new, Glass::new);
    static final int SIZE = constructors.size();
    private static SplittableRandom rand = new SplittableRandom(42);

    public static Trash random() {
        Function<Double, Trash> doubleTrashFunction = constructors.get(rand.nextInt(SIZE));
        Trash apply = doubleTrashFunction.apply(rand.nextDouble());
        return apply;
    }
}

public class RecycleA {
    public static void main(String[] args) {
        List<Trash> bin = Stream.generate(SimpleFactory::random).limit(10).toList();

        Bins bins = new Bins(bin);
        bins.show();
    }

}
