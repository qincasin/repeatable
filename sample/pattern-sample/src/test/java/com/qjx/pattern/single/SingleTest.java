package com.qjx.pattern.single;


import org.junit.jupiter.api.Test;

public class SingleTest {
    @Test
    public void test() {
        Single<Double> pi = new Single<>(Double.valueOf(3.14));
        Double a = pi.get();
        System.out.println(a);
    }
}
