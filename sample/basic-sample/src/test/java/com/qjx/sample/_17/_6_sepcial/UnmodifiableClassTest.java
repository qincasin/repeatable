package com.qjx.sample._17._6_sepcial;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 09:43
 * @Description:
 */
public class UnmodifiableClassTest {
    @Test
    public void test() {
        var list = List.of(1,2,3,4);

        var res = list.stream().map(i -> i * 2).toList();

        System.out.println(res);
    }


}
