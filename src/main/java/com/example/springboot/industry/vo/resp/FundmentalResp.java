package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 涨停出参
 */

@Data
public class FundmentalResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double close;
    private Double high;
    private Double low;
    private Double preClose;
    private Double amp;
    private Double peTtm;
    private Double pb;
    private Double psTtm;
    private Double revAssetRatio;
    private Double profitRate;
    private Double roe;
    private Double turnoverRate;
    private Double amount;
    private String market;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
