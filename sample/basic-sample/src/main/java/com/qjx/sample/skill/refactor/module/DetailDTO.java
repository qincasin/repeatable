package com.qjx.sample.skill.refactor.module;

import java.util.Date;
import lombok.Data;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
@Data
public class DetailDTO {

    /**
     * 业务序列号
     */
    private String bizSeq;
    private String amt;
    private Date date;
    private Integer status;


    public static DetailDTO convert(String origin) {
        return new DetailDTO();
    }

}
