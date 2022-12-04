package com.qjx.sample._17;

/**
 * 
 * @author: qinjiaxing
 * @Date: 2022/11/30 22:37
 * @Description:
 */
public record Circle(int num) {
    public Circle {
        if (num < 0) {
            throw new IllegalArgumentException("数值类型不能小于0");
        }
    }
}

