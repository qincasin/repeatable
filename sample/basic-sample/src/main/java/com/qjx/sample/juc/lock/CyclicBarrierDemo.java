package com.qjx.sample.juc.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;

/**
 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
 */
public class CyclicBarrierDemo {
    int runner = 3;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);

    public void runner() {
        final Random random = new Random();
        Stream.of("A", "B", "C").forEach(t -> new Thread(() -> {
            long prepareTime = random.nextInt(10000) + 100;
            System.out.println(t + "  is preparing for time:" + prepareTime);
            try {
                Thread.sleep(prepareTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                System.out.println(t + "  is prepared, waiting for others");
                cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(t + "  starts running"); // 所有运动员都准备好了，一起开始跑
        }, t).start());
    }

    public static void main(String[] args) {
        CyclicBarrierDemo d = new CyclicBarrierDemo();
        d.runner();
    }
}
