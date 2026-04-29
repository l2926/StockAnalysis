package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 行业统计概览——表格 出参
 */

@Data
public class StatisticsAllExcelResp {
    private Integer idx;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Integer ct;
    private Double mv;
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
