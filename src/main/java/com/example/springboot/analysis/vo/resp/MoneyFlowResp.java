package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc 资金流向出参
 * @author lizijian
 * @date 2025-06-13
 */

@Data
public class MoneyFlowResp {
    private Integer idx;
    private String conceptName;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double buyElgAmount;
    private Double buyLgAmount;
    private Double buyMdAmount;
    private Double buySmAmount;
    private Double sellElgAmount;
    private Double sellLgAmount;
    private Double sellMdAmount;
    private Double sellSmAmount;
    private Double netMfAmount;
    private Double amount;
    private Double turnoverRate;
    private String market;
    private Double totalMv;
    private Double pb;
    private Double assets;
    private String tradeDate;
}
