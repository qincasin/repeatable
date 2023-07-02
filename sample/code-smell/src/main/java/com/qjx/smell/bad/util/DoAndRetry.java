package com.qjx.smell.bad.util;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by libing2 on 2020/10/24
 */
public class DoAndRetry {
    private static final Logger LOG = LoggerFactory.getLogger(DoAndRetry.class);

    /**
     * @param retryThreshold,   如果失败了，要重试的次数。<0：则按0处理；0：执行失败后不重试；1执行失败后，重试1次......
     * @param intervalInMillis, 如果失败了，重试之前要sleep的秒数
     * @param doFunc
     * @param paraT
     * @param paraU
     * @param checkRtnFunc,     注意：检查结果函数抛出异常时，也进行重试
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <T, U, R> DResult<R> doItAndRetryIfFailed(int retryThreshold,
                                                            long intervalInMillis,
                                                            BiFunction<T, U, R> doFunc,
                                                            T paraT,
                                                            U paraU,
                                                            Predicate<R> checkRtnFunc) {
        retryThreshold = Math.max(retryThreshold, 0);
        int executedCount = 0;
        Exception ex = null;
        R r = null;

        while (executedCount <= retryThreshold) {
            executedCount++;
            ex = null;
            try {
                r = doFunc.apply(paraT, paraU);
                // 检查结果函数抛出异常时，也进行重试
                if (checkRtnFunc.test(r)) {
                    return DResult.create(true, r, null);
                } else {
                    logWhenFailed(retryThreshold, paraT, paraU, executedCount, null);
                }
            } catch (Exception e) {
                ex = e;
                logWhenFailed(retryThreshold, paraT, paraU, executedCount, e);
            }
            // 来到这里，肯定是失败啦
            sleepIfNecessary(retryThreshold, intervalInMillis, executedCount);
        }

        return DResult.create(false, r, ex);
    }

    private static <T, U> void logWhenFailed(int retryThreshold,
                                             T paraT, U paraU,
                                             int executedCount, Exception e) {
        String dueTo = (e == null) ? "Retry due to testing return false." : "Retry due to exception.";
        String finalFailed = (executedCount - 1) == retryThreshold ? "Finally failed." : "";
        String info = String.format("%s %s paraT=%s, paraU=%s, alreadyExecutedCount=%s, alreadyRetryCount= %s/%s",
                dueTo, finalFailed, paraT, paraU, executedCount, executedCount - 1, retryThreshold);
        if (e == null) {
            LOG.info(info);
        } else {
            LOG.info(info, e);
        }
    }

    private static void sleepIfNecessary(int retryThreshold, long intervalInMillis, int executedCount) {
        int alreadyRetryCount = executedCount - 1;
        if (alreadyRetryCount < retryThreshold) {
            try {
                Thread.sleep(intervalInMillis);
            } catch (InterruptedException e) {
                LOG.error("InterruptedException happened, ignore and continue...", e);
            }
        }
    }

    public static class DResult<R> {
        public final boolean success;
        public final R r;
        public final Exception e;

        private DResult(final boolean success, final R r, final Exception e) {
            this.success = success;
            this.r = r;
            this.e = e;
        }

        public static <R> DResult<R> create(final boolean success, final R r, final Exception e) {
            return new DResult<>(success, r, e);
        }
    }

    public static void rethrow(Exception e) {
        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        } else {
            LOG.error("rethrow encounter checked exception.", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int retryThreshold = 5;
        DResult<Integer> dr = doItAndRetryIfFailed(
                retryThreshold,
                1000,
                (e1, e2) -> doFunc(retryThreshold),
                "", "",
                e -> testFunc(e));
        System.out.println(dr.success + ", " + dr.r);
    }

    private static int i = 0;

    private static int doFunc(int threshold) {
        if (i <= threshold) {
            i++;
            throw new RuntimeException("do e");
        }
        return new Random().nextInt();
    }

    private static boolean testFunc(int i) {
        return true;
    }

}

