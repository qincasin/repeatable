package com.qjx.sample.scrach;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/30 09:47
 * @Description:
 */
@NoArgsConstructor
@Data
public class PodInfo {
    private String appName;
    private Object instanceName;
    private Integer metricCnt;
    private Object scanFromMills;
    private Object scanToMills;
    private String startTime;
    private String endTime;
}
