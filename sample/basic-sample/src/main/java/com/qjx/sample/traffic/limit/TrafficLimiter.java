package com.qjx.sample.traffic.limit;

/**
 * @author qinjiaxing on 2023/7/2
 * @author <others>
 */
public interface TrafficLimiter {

    /**
     * true 表示限流，false 表示未被限流
     *
     * @return
     */
    boolean limit();

}
