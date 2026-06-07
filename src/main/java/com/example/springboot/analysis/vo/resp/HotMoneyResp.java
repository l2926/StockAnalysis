package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * 游资交易明细
 */

@Data
public class HotMoneyResp {
    private Integer idx;
    private String tradeDate;
    private String tsCode;
    private String tsName;
    private Double buyAmount;
    private Double sellAmount;
    private Double netAmount;
    private String hmName;
    private String hmOrgs;
}
