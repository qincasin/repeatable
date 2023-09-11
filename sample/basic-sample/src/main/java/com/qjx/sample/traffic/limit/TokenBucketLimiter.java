package com.qjx.sample.traffic.limit;

/**
 * 令牌桶算法
 *
 * @author qinjiaxing on 2023/7/2
 * @author <others>
 */
public class TokenBucketLimiter implements TrafficLimiter {

    private long timestamp = System.currentTimeMillis();
    // 桶的容量
    private long capacity = 100;
    // 令牌生成速率
    private long rate = 10;
    // 当前令牌数量
    private long tokens = 20;

    @Override
    public boolean limit() {
        long now = System.currentTimeMillis();
        tokens = Math.min(capacity, tokens + (now - timestamp) * rate);
        timestamp = now;
        if (tokens < 1) {
            // 若不到1个令牌,则拒绝
            return true;
        } else {
            // 还有令牌，领取令牌
            tokens--;
            return false;
        }
    }
}
