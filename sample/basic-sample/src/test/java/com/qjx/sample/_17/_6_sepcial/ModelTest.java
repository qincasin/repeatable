package com.qjx.sample._17._6_sepcial;

import org.junit.jupiter.api.Test;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 09:28
 * @Description:
 */
class ModelTest {
    /**
     * 这段代码使用 of() 方法构建新对象，withXX() 方法修改单个参数，toBuilder() 方法修改多个参数，修改后会返回新的对象。
     * 这种代码编写方式保证了数据的不可变性，让我们的开发更加简单、可回溯、测试友好，减少了任何可能的副作用
     */
    @Test
    public void test() {
        //构建model
        var model_01 = Model.of("101", "model_01", "model");

        //空的model
        var model_02 = Model.of();

        //构建指定参数的model
        var model_3 = Model.builder().id("301").name("model_3").build();

        //修改Model的一个值，通过@With来生成一个全新的model
        var model_4 = model_01.withName("model_01_update");

        var model_5 = model_01.toBuilder().name("model_05").type("new_model").build();

        System.out.println(model_5);
    }
}