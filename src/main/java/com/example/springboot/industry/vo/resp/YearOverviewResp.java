package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 年度行情纵览出参
 */

@Data
public class YearOverviewResp {
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
    private Double yearPct15;
    private Double yearPct14;
    private Double yearPct13;
    private Double yearPct12;
    private Double yearPct11;
    private Double yearPct10;
    private Double yearPct9;
    private Double yearPct8;
    private Double yearPct7;
    private Double yearPct6;
    private Double yearPct5;
    private Double yearPct4;
    private Double yearPct3;
    private Double yearPct2;
    private Double yearPct1;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
