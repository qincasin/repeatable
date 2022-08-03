package com.qjx.shein.migration.coorworker;

import java.util.List;

/**
 * 用来协调TheWorker的协调者。其实现类中的 启动/终止/暂停/恢复/改变线程数量 都是synchronized的，因为对于同一个Coordinator实例 *不允许* 同时进行修改
 * <p>
 * 每一个TheCoordinator实例，负责自己所有的TheWorker实例
 * <p>
 * 如果处理多个事情时需要多个Coordinator，则需要创建多个Coordinator实例
 * <p>
 * Coordinator为每一个TheWorker实例创建一个线程池，一个只有单一一个线程的线程池。TheWorker之间毫无关联，各自执行自己的事情
 */
public interface TheCoordinator {
    void start();

    void terminate();

    void terminateAtBarrier();

    void pause();

    void pauseAtBarrier();

    void resume();

    void changeThreadCount(int newThreadCout);

    String readableCoordinator();

    String status();

    TheWorker createWorker(int threadSeq);

//    Barrier calculateBarrierByItems(List<BarrierItem> items);

    void markGlobalException();

    boolean hasGlobalException();

    int getRunningThreadCount();

}
