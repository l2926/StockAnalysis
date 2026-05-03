package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 行业行情概览 出参
 */

@Data
public class StatisticsExcelResp {
    private Integer idx;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Integer ct;
    private Integer allCt;
    private Double ctPct;
    private Double mv;
    private Double allMv;
    private Double mvPct;
    private Double pctChg;
    private Double amt;
    private Double allAmt;
    private Double amtPct;
    private String tradeDate;
}
