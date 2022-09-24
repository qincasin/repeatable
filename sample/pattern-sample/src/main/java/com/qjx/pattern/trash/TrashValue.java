package com.qjx.pattern.trash;


import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 13:24
 * @Description: sum 工具类
 */
public class TrashValue {
    private static double total;

    public static void sum(List<? extends Trash> bin, String type) {
        total = 0.0;
        bin.forEach(t -> {
            System.out.println(t);
            total += t.weight * t.price();
        });
        System.out.printf("total %s value = %.2f%n", type, total);
    }
}
