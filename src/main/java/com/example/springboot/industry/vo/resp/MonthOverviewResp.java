package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 涨停出参
 */

@Data
public class MonthOverviewResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String province;
    private String city;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double monthPct15;
    private Double monthPct14;
    private Double monthPct13;
    private Double monthPct12;
    private Double monthPct11;
    private Double monthPct10;
    private Double monthPct9;
    private Double monthPct8;
    private Double monthPct7;
    private Double monthPct6;
    private Double monthPct5;
    private Double monthPct4;
    private Double monthPct3;
    private Double monthPct2;
    private Double monthPct1;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
