package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 年度行情概览 出参
 */

@Data
public class YearOverviewResp {
    private Integer idx;
    private String indexCode;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double year15;
    private Double year14;
    private Double year13;
    private Double year12;
    private Double year11;
    private Double year10;
    private Double year9;
    private Double year8;
    private Double year7;
    private Double year6;
    private Double year5;
    private Double year4;
    private Double year3;
    private Double year2;
    private Double year1;
    private Double pctChange;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
