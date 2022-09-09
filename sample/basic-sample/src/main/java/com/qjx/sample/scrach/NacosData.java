package com.qjx.sample.scrach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class NacosData {

    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("info")
    private String info;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("content")
        private List<ContentDTO> content;
        @JsonProperty("pageable")
        private PageableDTO pageable;
        @JsonProperty("last")
        private Boolean last;
        @JsonProperty("totalPages")
        private Integer totalPages;
        @JsonProperty("totalElements")
        private Integer totalElements;
        @JsonProperty("number")
        private Integer number;
        @JsonProperty("sort")
        private SortDTO sort;
        @JsonProperty("first")
        private Boolean first;
        @JsonProperty("numberOfElements")
        private Integer numberOfElements;
        @JsonProperty("size")
        private Integer size;
        @JsonProperty("empty")
        private Boolean empty;

        @NoArgsConstructor
        @Data
        public static class PageableDTO {
            @JsonProperty("sort")
            private SortDTO sort;
            @JsonProperty("pageNumber")
            private Integer pageNumber;
            @JsonProperty("pageSize")
            private Integer pageSize;
            @JsonProperty("offset")
            private Integer offset;
            @JsonProperty("paged")
            private Boolean paged;
            @JsonProperty("unpaged")
            private Boolean unpaged;

            @NoArgsConstructor
            @Data
            public static class SortDTO {
                @JsonProperty("sorted")
                private Boolean sorted;
                @JsonProperty("unsorted")
                private Boolean unsorted;
                @JsonProperty("empty")
                private Boolean empty;
            }
        }

        @NoArgsConstructor
        @Data
        public static class SortDTO {
            @JsonProperty("sorted")
            private Boolean sorted;
            @JsonProperty("unsorted")
            private Boolean unsorted;
            @JsonProperty("empty")
            private Boolean empty;
        }

        @NoArgsConstructor
        @Data
        public static class ContentDTO {
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
    }
}
