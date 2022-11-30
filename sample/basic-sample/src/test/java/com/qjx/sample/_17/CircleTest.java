package com.qjx.sample._17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: qinjiaxing
 * @Date: 2022/11/30 22:39
 * @Description:
 */
class CircleTest {

    @Test
    public void test(){
        Circle circle = new Circle(10);
        System.out.println(circle.num());

        Circle circle1 = new Circle(10);
        System.out.println(circle.num());

        System.out.println(circle == circle1);
        System.out.println(circle.equals(circle1));

    }

    @Test
    public void testRecordLessThanZero(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Circle c = new Circle(-1);
            c.num();
        });
        assertEquals("数值类型不能小于0",ex.getMessage());
    }

}