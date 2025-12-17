package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 涨停出参
 */

@Data
public class TenDaysMarketResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private Double pctChg;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double yPct1;
    private Double yPct2;
    private Double yPct3;
    private Double yPct4;
    private Double yPct5;
    private Double yPct6;
    private Double yPct7;
    private Double yPct8;
    private Double yPct9;
    private Double yPct10;
    private Double yPct11;
    private Double yPct12;
    private Double yPct13;
    private Double yPct14;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
