package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 周行情概览 出参
 */

@Data
public class WeekOverviewResp {
    private Integer idx;
    private String indexCode;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double week15;
    private Double week14;
    private Double week13;
    private Double week12;
    private Double week11;
    private Double week10;
    private Double week9;
    private Double week8;
    private Double week7;
    private Double week6;
    private Double week5;
    private Double week4;
    private Double week3;
    private Double week2;
    private Double week1;
    private Double pctChange;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}
