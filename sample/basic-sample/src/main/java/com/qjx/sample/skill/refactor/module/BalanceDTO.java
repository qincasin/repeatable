package com.qjx.sample.skill.refactor.module;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
@Data
public class BalanceDTO {

    private String key;
    /**
     * 业务序列号
     */
    private String bizSeq;
    private String balance;
    private Date date;
    private Integer status;
    private String type;


    public static BalanceDTO convert(String line) {
        BalanceDTO dto = new BalanceDTO();
        String[] dataLine = line.split(",", -1);
        dto.setBalance(dataLine[1]);
        dto.setType(dataLine[2]);
        return dto;
    }

}
