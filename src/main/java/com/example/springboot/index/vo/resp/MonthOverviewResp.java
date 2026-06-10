package com.example.springboot.index.vo.resp;

/**
 * 月行情概览 出参
 */

import lombok.Data;

@Data
public class MonthOverviewResp {
    private Integer idx;
    private String indexCode;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double month15;
    private Double month14;
    private Double month13;
    private Double month12;
    private Double month11;
    private Double month10;
    private Double month9;
    private Double month8;
    private Double month7;
    private Double month6;
    private Double month5;
    private Double month4;
    private Double month3;
    private Double month2;
    private Double month1;
    private Double pctChange;
    private Double pb;
    private Double totalMv;
    private String tradeDate;
}
