package com.qjx.sample.invoke.mark;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public class SpringHttpRequestMarkEnhancer extends AbstractMarkEnhancer<HttpHeaders> {

    public SpringHttpRequestMarkEnhancer(MarkFinder... finders) {
        if (finders != null) {
            for (MarkFinder finder : finders) {
                markFinders.add(finder);
            }
        }
    }

    @Override
    protected void mark(HttpHeaders httpHeaders) {
        List<String> kvs = new ArrayList<>();
        for (MarkFinder markFinder : markFinders) {
            markFinder.find().forEach((k, v) -> kvs.add(MarkConstants.appendKv(k, v)));
        }
        // append header
        if (!kvs.isEmpty()) {
            httpHeaders.addAll(MarkConstants.from, kvs);
        }
    }
}
