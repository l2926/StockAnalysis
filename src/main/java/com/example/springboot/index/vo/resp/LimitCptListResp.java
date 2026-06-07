package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 最强板块
 */

@Data
public class LimitCptListResp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String tradeDate;
    private Integer days;
    private String upStat;
    private Integer consNums;
    private Integer upNums;
    private Double pctChg;
    private Integer rank;
}
