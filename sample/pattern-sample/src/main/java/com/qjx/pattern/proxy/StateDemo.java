package com.qjx.pattern.proxy;


/**
 * @author: qinjiaxing
 * @Date: 2022/9/12 15:19
 * @Description: 基本的状态模式。
 * 在代理模式的基础上增加了更多的实现，以及在代理类的声明周期内切换实现的方法。
 * <p>
 * 有相同类型组成的状态模式
 */
class State {
    private State implementation;

    protected State() {

    }

    public State(State implementation) {
        this.implementation = implementation;
    }

    public void change(State state) {
        this.implementation = state;
    }

    //将方法调用转发到实现类
    public void f() {
        implementation.f();
    }

    public void g() {
        implementation.f();
    }

    public void h() {
        implementation.h();
    }
}

class Implementation1 extends State {


    @Override
    public void f() {
        System.out.println("f()");
    }

    @Override
    public void g() {
        System.out.println("g()");
    }

    @Override
    public void h() {
        System.out.println("h()");
    }
}

class Implementation3 extends State {
    @Override
    public void f() {
        System.out.println("f3()");
    }

    @Override
    public void g() {
        System.out.println("g3()");
    }

    @Override
    public void h() {
        System.out.println("h3()");
    }
}


public class StateDemo {
    public static void main(String[] args) {
        State state = new State(new Implementation1());
        test(state);
        System.out.println("开始改变实现类");
        state.change(new Implementation3());
        test(state);
    }

    private static void test(State state) {
        state.f();
        state.g();
        state.h();
    }
}
