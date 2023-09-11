package com.qjx.sample.traffic.limit;

import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

/**
 * 滑动窗口限流算法
 *
 * @author qinjiaxing on 2023/7/2
 * @author <others>
 */
@Slf4j
public class SlidingTimeWindowLimiter implements TrafficLimiter {

    // 服务在最近1秒内的访问次数，可以放在redis中，实现分布式系统的访问次数
    private int reqCount;
    // 使用LinkedList 来记录滑动窗口的10个格子
    private LinkedList<Integer> slots = new LinkedList<>();

    // 每秒限流的的最大请求数
    private int limitNum = 100;
    // 滑动窗口里的每个格子的时间长度，单位ms
    private long windowLength = 100L;

    // 滑动时间窗口里的格子数量
    private int windowNum = 10;

    public SlidingTimeWindowLimiter() {
        // 额外的线程去自动扩容格子，自动添加值,维护reqCount 值
        slots.add(0);
        new Thread(() -> {
            while (true) {
                try {
                    // 休眠时长：每个格子的时间长短 默认 100ms
                    Thread.sleep(windowLength);
                } catch (InterruptedException e) {
                }
                // TODO 这一部分 会有 并发问题
                slots.addLast(0);
                if (slots.size() > windowNum) {
                    reqCount = reqCount - slots.peekFirst();
                    slots.removeFirst();
                    log.info("滑动格子：{}", reqCount);
                }
            }
        }).start();

    }

    @Override
    public boolean limit() {
        // 判断当前时间窗口请求次数 + 1 是否超过每秒限流的最大请求以
        if (reqCount + 1 > limitNum) {
            return true;
        }
        // 将最后一个slot的值取出来 +1，放在最新的slot里
        slots.set(slots.size() - 1, slots.peekLast() + 1);
        reqCount++;
        return false;
    }
}
