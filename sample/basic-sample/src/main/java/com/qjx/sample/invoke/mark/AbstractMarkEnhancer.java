package com.qjx.sample.invoke.mark;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public abstract class AbstractMarkEnhancer<T> implements MarkEnhancer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected List<MarkFinder> markFinders = new ArrayList<>();

    @Override
    public void mark() {
    }

    /**
     * 开始标记当前服务实例
     *
     * @param context
     */
    protected abstract void mark(T context);
}
