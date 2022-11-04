package com.qjx.sample.scrach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/30 09:48
 * @Description:
 */
@NoArgsConstructor
@Data
public class EventPodInfoResp {

    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("info")
    private String info;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("pod_oom")
        private List<PodInfo> podOom;
        @JsonProperty("fail_restart")
        private List<PodInfo> failRestart;
        @JsonProperty("pod_unhealthy")
        private List<PodInfo> podUnhealthy;

    }
}
