package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 地域统计概览——表格 出参
 */

@Data
public class StatisticsAreaExcelResp {
    private Integer idx;
    private String province;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String indexCodeL1;
    private String indexCodeL2;
    private String indexCodeL3;
    private Integer ct;
    private Double mv;
    private Double avgMv;
    private Double asset;
    private Double income;
    private Double profit;
    private Double amt;
    private Double pe;
    private Double pb;
    private Double ps;
    private Double roe;
    private Double profitRate;
    private Double turnover;
    private String tradeDate;
}
