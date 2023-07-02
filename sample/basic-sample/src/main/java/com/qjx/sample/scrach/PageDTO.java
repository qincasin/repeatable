package com.qjx.sample.scrach;

import lombok.Data;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/16
 * @author <others>
 */
@Data
public class PageDTO {
    private String name;
    private String owner;
    private String gitBaseUrl;
    private String typeName;
    private String gitlabProjectId;
    private String description;
}
