package com.qjx.smell;

public class BadSmellTest {
    private void sendBook() {
        execute(this::sendBook);
    }

    public void execute(final Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            sendFail();
        }
    }

    private void sendFail() {
        System.out.println("sent fail....");
    }
}
