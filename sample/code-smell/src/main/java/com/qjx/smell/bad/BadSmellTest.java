package com.qjx.smell.bad;

public class BadSmellTest {
    private void sendBook() {
        execute(this::sendBook);
    }

    private void sendFail() {
        System.out.println("sent fail....");
    }

    private void execute(final Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            sendFail();
        }
    }

}
