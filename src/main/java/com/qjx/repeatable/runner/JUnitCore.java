package com.qjx.repeatable.runner;

import com.qjx.repeatable.runner.notification.RunNotifier;

public class JUnitCore {

    private final RunNotifier notifier = new RunNotifier();

    public Result run(Request request){
        return null;
//        return run(request.get)
    }


    public static void main(String[] args) {

        JUnitCore jUnitCore = new JUnitCore();


//        Result result = jUnitCore.run(new System(),"NonExistentTest");

//        Result result = new JUnitCore().notifier

    }
}
