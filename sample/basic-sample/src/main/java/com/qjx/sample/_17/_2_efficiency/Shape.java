package com.qjx.sample._17._2_efficiency;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 11:10
 * @Description:
 */
public sealed interface Shape permits Shape.Circle, Shape.Square, Shape.Rectangel {
    record Circle(double radium) implements Shape {
    }

    record Square(double side) implements Shape {
    }

    record Rectangel(double length, double width) implements Shape {
    }
}
