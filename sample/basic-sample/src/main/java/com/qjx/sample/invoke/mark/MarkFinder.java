package com.qjx.sample.invoke.mark;

import java.util.Map;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public interface MarkFinder {

    Map<String, String> find(String... filterKeys);

}
