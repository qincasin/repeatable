package com.qjx.pattern.state;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/12 15:42
 * @Description: 状态机模式
 * 将系统从一种状态改变为另一种状态的代码，通常使用了模板方法
 */

interface State {
    void run();
}

//状态机的抽象类
abstract class StateMachine {
    protected State currentState;

    protected abstract boolean changeState();

    //模板方法
    protected final void runAll() {
        while (changeState()) {
            currentState.run();
        }
    }
}

//为每种状态实现一个不同的子类
class Wash implements State{
    @Override
    public void run() {
        System.out.println("washing...");
    }
}

class Spin implements State{
    @Override
    public void run() {
        System.out.println("spinning...");
    }
}

//冲洗
class Rinse implements State{
    @Override
    public void run() {
        System.out.println("rinsing...");
    }
}
class Washer extends StateMachine{
    private Iterator<State> state =
            Arrays.asList(new Wash(), new Spin(), new Rinse(), new Spin()).iterator();

    public Washer () {
        runAll();
    }

    @Override
    protected boolean changeState() {
        if (!state.hasNext()){
            return false;
        }
        //将代理引用指向一个新的state对象
        currentState = state.next();
        return true;
    }
}

public class StateMachineDemo {
    public static void main(String[] args) {
        new Washer();
    }
}
