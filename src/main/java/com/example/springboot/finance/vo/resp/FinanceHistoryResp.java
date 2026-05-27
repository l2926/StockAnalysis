package com.example.springboot.finance.vo.resp;

import lombok.Data;

/**
 * 财务历史概览
 */

@Data
public class FinanceHistoryResp {
    private Integer idx;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double num2010;
    private Double num2011;
    private Double num2012;
    private Double num2013;
    private Double num2014;
    private Double num2015;
    private Double num2016;
    private Double num2017;
    private Double num2018;
    private Double num2019;
    private Double num2020;
    private Double num2021;
    private Double num2022;
    private Double num2023;
    private Double num2024;
    private Double num2025;
    private String market;
    private Double asset;
    private Double pb;
    private Double totalMv;
    private String tradeDate;
}
