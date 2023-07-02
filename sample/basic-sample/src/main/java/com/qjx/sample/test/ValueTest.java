package com.qjx.sample.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/22
 * @author <others>
 */
@Component
public class ValueTest {
    //#{'${susan.test.set:}'.empty ? null : '${susan.test.set:}'.split(',')
    // @Value("${test.value1} || ${usdk.test.value1}")
    @Value("#{'${test.value1:}'.empty ? '${usdk.test.value1:}':'${test.value1:}' }")
    private String value;

    public String printValue(){
        System.out.println(value);
        return value;
    }

}
