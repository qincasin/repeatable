package com.qjx.sample.invoke;

import java.util.HashMap;
import java.util.Map;
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
public class InvokeMetaDataConfigProperties {

    private DirectData direct = new DirectData();
    private GatewayData gateway = new GatewayData();


    @Setter
    @Getter
    public static class MetaData {

        private Map<String, String> target = new HashMap<>();

    }

    @Setter
    @Getter
    public static class ExtData {

    }

    @Getter
    @Setter
    public static class DirectData extends MetaData {

    }

    @Setter
    @Getter
    public static class GatewayData extends MetaData {

        private String targetUrl;
        private GatewayExt ext = new GatewayExt();
    }

    @Setter
    @Getter
    public static class GatewayExt extends ExtData {

        // 用于直接读取 usdk.invocation.data.gateway.ext.host 下面配置的值； 如果指定了服务优先的策略，则该值不会被使用；
        private String hostUrl;
        private Map<String, String> host = new HashMap<>();
    }


}
