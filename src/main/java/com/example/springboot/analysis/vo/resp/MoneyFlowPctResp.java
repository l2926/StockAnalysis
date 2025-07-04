package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc 资金流向 百分比 出参
 * @author lizijian
 * @date 2025-06-13
 */

@Data
public class MoneyFlowPctResp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double buyElgAmountPct;
    private Double buyLgAmountPct;
    private Double buyMdAmountPct;
    private Double buySmAmountPct;
    private Double sellElgAmountPct;
    private Double sellLgAmountPct;
    private Double sellMdAmountPct;
    private Double sellSmAmountPct;
    private Double netMfAmount;
    private Double amount;
    private Double turnoverRate;
    private String market;
    private Double totalMv;
    private Double pb;
    private Double assets;
    private String tradeDate;
}
