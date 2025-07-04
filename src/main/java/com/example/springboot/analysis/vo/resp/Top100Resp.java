package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc A股百强名单 出参
 * @author lizijian
 * @date 2025-06-12
 */

@Data
public class Top100Resp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double close;
    private Double peTtm;
    private Double pb;
    private Double psTtm;
    private Double roe;
    private Double profitRate;
    private Double turnoverRate;
    private Double amount;
    private Integer up10Count5;
    private Integer up10Count20;
    private String market;
    private Double totalMv;
    private Double assets;
    private String tradeDate;
}
