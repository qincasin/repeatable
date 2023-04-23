package com.qjx.sample.invoke;


import lombok.Getter;
import lombok.Setter;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Setter
@Getter
public class InvokePolicyConfigProperties {

    private boolean enabled = false;

    // 用于直接读取 usdk.invocation.policy 下面配置的值； 如果指定了服务优先的策略，则该值不会被使用；
    private String policyType;

}
