package com.qjx.sample.invoke.mark;

import feign.RequestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public class FeignRequestMarkEnhancer extends AbstractMarkEnhancer<RequestTemplate> {

    public FeignRequestMarkEnhancer(MarkFinder... finders) {
        if (finders != null) {
            markFinders.addAll(Arrays.asList(finders));
        }
    }


    @Override
    protected void mark(RequestTemplate requestTemplate) {
        try {
            List<String> kvs = new ArrayList<>();
            // 添加
            for (MarkFinder markFinder : markFinders) {
                markFinder.find().forEach((k, v) -> kvs.add(MarkConstants.appendKv(k, v)));
            }
            if (!kvs.isEmpty()) {
                requestTemplate.header(MarkConstants.from, kvs);
                if (!requestTemplate.headers().containsKey(MarkConstants.orig)) {
                    // 初始化orig
                    requestTemplate.header(MarkConstants.orig, kvs);
                }
            }
        } catch (Exception e) {
            logger.warn("mark error", e);
        }
    }
}
