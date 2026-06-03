package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 日度行情纵览 出参
 */

@Data
public class DailyOverviewResp {
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
    private Double dayPct15;
    private Double dayPct14;
    private Double dayPct13;
    private Double dayPct12;
    private Double dayPct11;
    private Double dayPct10;
    private Double dayPct9;
    private Double dayPct8;
    private Double dayPct7;
    private Double dayPct6;
    private Double dayPct5;
    private Double dayPct4;
    private Double dayPct3;
    private Double dayPct2;
    private Double dayPct1;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
