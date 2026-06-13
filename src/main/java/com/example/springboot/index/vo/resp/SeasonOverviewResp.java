package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 季度行情概览 出参
 */

@Data
public class SeasonOverviewResp {
    private Integer idx;
    private String indexCode;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double season15;
    private Double season14;
    private Double season13;
    private Double season12;
    private Double season11;
    private Double season10;
    private Double season9;
    private Double season8;
    private Double season7;
    private Double season6;
    private Double season5;
    private Double season4;
    private Double season3;
    private Double season2;
    private Double season1;
    private Double pctChange;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
