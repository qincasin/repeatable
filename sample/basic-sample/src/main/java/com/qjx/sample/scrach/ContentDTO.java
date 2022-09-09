package com.qjx.sample.scrach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContentDTO {
    @JsonProperty("envName")
    private String envName;
    @JsonProperty("appName")
    private String appName;
    @JsonProperty("deployName")
    private String deployName;
    @JsonProperty("dataId")
    private String dataId;
    @JsonProperty("group")
    private String group;
    @JsonProperty("lastDiffInfo")
    private String lastDiffInfo;
    @JsonProperty("lastModifyVersion")
    private String lastModifyVersion;
    @JsonProperty("lastModifyUser")
    private String lastModifyUser;
    @JsonProperty("lastModifyTime")
    private Long lastModifyTime;
    @JsonProperty("lastContent")
    private String lastContent;
    @JsonProperty("instanceInfoList")
    private Object instanceInfoList;
    @JsonProperty("waitApproval")
    private Boolean waitApproval;
    @JsonProperty("approvalStatus")
    private Integer approvalStatus;
    @JsonProperty("applyUser")
    private Object applyUser;
    @JsonProperty("applyTime")
    private Object applyTime;
    @JsonProperty("modifyApplyOpType")
    private Object modifyApplyOpType;
    @JsonProperty("applyDiffInfo")
    private Object applyDiffInfo;
    @JsonProperty("approvalUser")
    private Object approvalUser;
    @JsonProperty("approvalOpUser")
    private Object approvalOpUser;
    @JsonProperty("allowApproval")
    private Boolean allowApproval;
    @JsonProperty("allowModify")
    private Boolean allowModify;

}
