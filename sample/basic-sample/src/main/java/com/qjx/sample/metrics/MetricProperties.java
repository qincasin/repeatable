package com.qjx.sample.metrics;

import lombok.Data;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/23
 * @author <others>
 */
@Data
public class MetricProperties {
    private boolean enabled;
    private String key;
    private String value;
}
