package com.qjx.repeatable.runner.notification;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RunNotifier {

    public List<RunListener> listener = new CopyOnWriteArrayList<>();


}
