package com.qjx.pattern.migration.coorworker;


/**
 * 它非常的令人放心，永远不存在多线程访问的问题
 * <p>
 * TheWorker被Coordinator所管理，Coordinator可以 启动/终止/暂停/恢复 该Coordinator所管理的全部TheWorker
 *
 * <p>
 */
public interface TheWorker {

    String readableWorker();

//    BarrierItem doGenerateBarrierItem();

//    void doCatchBarrier(Barrier barrier);

    void doTheWork();

    void endlesslyRun();

//    BarrierItem waitForGenerateBarrierItem();

    void waitForCatchBarrier();

    void waitForPause();

    void waitForTerminate();

    void signalToCatchBarrier();

    void signalToPause();

    void signalToRun();

    void signalToTerminate();

    void signalAllToReleaseWorkersWhenException();

//    void setBarrier(Barrier barrierIds);

//    WorkerStateEnum getState();

//    void setState(WorkerStateEnum state);

}