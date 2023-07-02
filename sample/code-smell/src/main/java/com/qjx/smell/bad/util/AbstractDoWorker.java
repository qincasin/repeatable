package com.qjx.smell.bad.util;// package com.qjx.sample.util;
//
// import cn.dotfashion.soa.eadis.exception.RedisOperationException;
// import cn.dotfashion.soa.framework.exception.BusinessException;
// import cn.dotfashion.soa.framework.util.ExceptionTools;
// import cn.dotfashion.soa.wish.cutover.constant.enums.SupportTableEnum;
// import cn.dotfashion.soa.wish.cutover.util.DoAndRetry;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.ibatis.exceptions.IbatisException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.RecoverableDataAccessException;
// import org.springframework.dao.TransientDataAccessException;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
// import java.sql.SQLException;
// import java.util.Arrays;
// import java.util.List;
// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.TimeUnit;
// import java.util.function.BiFunction;
//
// /**
//  * Created by qincasin on 2021/7/5.
//  */
//
// @Slf4j
// public abstract class AbstractDoWorker extends AbstractUserLoop {
//
//     @Autowired
//     private ThreadPoolTaskExecutor cleanThreadPoolTaskExecutor;
//
//     private static final List<Class<? extends Exception>> IGNORED_EXCEPTION =
//             Arrays.asList(
//                     SQLException.class,
//                     IbatisException.class,
//                     BusinessException.class,
//                     TransientDataAccessException.class,
//                     RecoverableDataAccessException.class,
//                     RedisOperationException.class
//             );
//
//
//     @Override
//     protected void doTheWork(Long oldMemberId, Integer oldBrandId, Long newMemberId, Integer newBrandId) {
//         CompletableFuture.allOf(Arrays.stream(SupportTableEnum.values())
//                 .map(a -> CompletableFuture.runAsync(new PersistenceJob(a.getTableName(), oldMemberId, newMemberId, a.getBrandId()), cleanThreadPoolTaskExecutor))
//                 .toArray(CompletableFuture[]::new))
//                 .join();
//         return;
//     }
//
//     private boolean considerAsGlobalException(Exception e) {
//         boolean yesGlobalException = true;
//         for (Class<? extends Exception> ignoredCls : IGNORED_EXCEPTION) {
//             if (ignoredCls.isInstance(e)) {
//                 yesGlobalException = false;
//                 break;
//             }
//         }
//         return yesGlobalException;
//     }
//
//     public class PersistenceJob implements Runnable {
//         private final String table;
//         private final Long oldMemberId;
//         private final Long newMemberId;
//         private final Integer brandId;
//
//         public PersistenceJob(String table, Long oldMemberId, Long newMemberId, Integer brandId) {
//             this.table = table;
//             this.oldMemberId = oldMemberId;
//             this.newMemberId = newMemberId;
//             this.brandId = brandId;
//         }
//
//         @Override
//         public void run() {
//             selectTable();
// //            if (SupportTableEnum.RS_WISHLIST_SHEIN.getTableName().equals(table)){
// //                log.info("开始模拟休眠......");
// //                当处理到给定表的时候 进行休眠一会，用来测试kafka长时间不ack 会有什么异常影响
// //                sleep();
// //                log.info("休眠结束");
// //            }
//         }
//
//         private void selectTable() {
//             if (brandId == 7) {
//                 if (SupportTableEnum.ARCHIVE_SHEIN.getTableName().equals(table)) {
//                     MultiFunction((a, b) -> doArchiveShein(oldMemberId, newMemberId), "a", "b", 1);
//                 } else {
//                     MultiFunction((a, b) -> doSheinBusinessTable(oldMemberId, newMemberId, table), "a", "b", 1);
//                 }
//             } else {
//                 if (SupportTableEnum.ARCHIVE_SHEIN.getTableName().equals(table)) {
//                     MultiFunction((a, b) -> doArchiveRomwe(oldMemberId, newMemberId), "a", "b", 1);
//                 } else {
//                     MultiFunction((a, b) -> doRomweBusinessTable(oldMemberId, newMemberId, table), "a", "b", 1);
//                 }
//             }
//         }
//     }
//
//
//     protected int doArchiveShein(Long oldMemberId, Long newMemberId) {
//         return 0;
//     }
//
//     protected int doArchiveRomwe(Long oldMemberId, Long newMemberId) {
//         return 0;
//     }
//
//     protected int doSheinBusinessTable(Long oldMemberId, Long newMemberId, String table) {
//         return 0;
//     }
//
//     protected int doRomweBusinessTable(Long oldMemberId, Long newMemberId, String table) {
//         return 0;
//     }
//
//     private <T, U> int MultiFunction(BiFunction<T, U, Integer> doFunc,
//                                      T parmT,
//                                      U parmU,
//                                      Integer r) {
//         for (; ; ) {
//             DoAndRetry.DResult<Integer> rst = DoAndRetry.doItAndRetryIfFailed(2, 200, (a, b) -> doFunc.apply(parmT, parmU), "a", "b", (affect) -> true);
//             // 最终失败了
//             if (rst.e != null) {
//                 log.error("重试遇到异常，需要抛出,异常原因={},{}", rst.e.getClass().getSimpleName(), rst.e);
//                 if (considerAsGlobalException(rst.e)) {
//                     //未知异常，应该抛出来
//                     DoAndRetry.rethrow(rst.e);
//                 } else {
//                     //给定异常中，继续重试下
//                     DoAndRetry.rethrow(rst.e);
//                 }
//             }
//             if (rst.r != null && rst.r == 0) {
//                 break;
//             }
//         }
//         return 0;
//     }
//
//     private void sleep(){
//         try {
//             TimeUnit.SECONDS.sleep(180);
//         } catch (InterruptedException e) {
//             log.error("e={}", ExceptionTools.getBusinessStackTrace(e));
//         }
//     }
// }