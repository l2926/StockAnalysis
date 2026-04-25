package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * 涨停出参
 */

@Data
public class WeekOverviewResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String province;
    private String city;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double weekPct15;
    private Double weekPct14;
    private Double weekPct13;
    private Double weekPct12;
    private Double weekPct11;
    private Double weekPct10;
    private Double weekPct9;
    private Double weekPct8;
    private Double weekPct7;
    private Double weekPct6;
    private Double weekPct5;
    private Double weekPct4;
    private Double weekPct3;
    private Double weekPct2;
    private Double weekPct1;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
