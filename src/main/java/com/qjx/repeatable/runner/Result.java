package com.qjx.repeatable.runner;

import com.qjx.repeatable.runner.notification.Failure;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Result implements Serializable {
    private final CopyOnWriteArrayList<Failure> failures;

    public Result() {
        this.failures = new CopyOnWriteArrayList<>();
    }

    public boolean wasSuccessful() {
        return getFailureCount() == 0;
    }

    /**
     * @return the number of tests that failed during the run
     */
    public int getFailureCount() {
        return failures.size();
    }
}
