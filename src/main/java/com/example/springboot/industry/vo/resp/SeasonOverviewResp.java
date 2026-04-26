package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 涨停出参
 */

@Data
public class SeasonOverviewResp {
    private Integer idx;
    private String name;
    private String tsCode;
//    private String province;
//    private String city;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double seasonPct15;
    private Double seasonPct14;
    private Double seasonPct13;
    private Double seasonPct12;
    private Double seasonPct11;
    private Double seasonPct10;
    private Double seasonPct9;
    private Double seasonPct8;
    private Double seasonPct7;
    private Double seasonPct6;
    private Double seasonPct5;
    private Double seasonPct4;
    private Double seasonPct3;
    private Double seasonPct2;
    private Double seasonPct1;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
