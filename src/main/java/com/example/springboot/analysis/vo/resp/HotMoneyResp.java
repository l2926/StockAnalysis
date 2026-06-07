package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * 游资交易明细
 */

@Data
public class HotMoneyResp {
    private Integer idx;
    private String tsCode;
    private String tsName;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String indexCodeL1;
    private String indexCodeL2;
    private String indexCodeL3;
    private Double pctChg;
    private Double peTtm;
    private Double pb;
    private Double psTtm;
    private Double amount;
    private Integer contiUp;

    private Double buyAmount;
    private Double sellAmount;
    private Double netAmount;
    private String hmName;
    private String hmOrgs;

    private String market;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
