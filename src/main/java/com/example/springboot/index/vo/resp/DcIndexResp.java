package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * @desc 东方财富概念板块出参
 * @author lizijian
 */

@Data
public class DcIndexResp {
    private String tsCode;
    private String tradeDate;
    private String name;
    private String leading;
    private String leadingCode;
    private Double pctChange;
    private Double leadingPct;
    private Double totalMv;
    private Double turnoverRate;
    private Integer upNum;
    private Integer downNum;
    private Integer totalNum;
    private Double upNumPct;
    private Double downNumPct;
}
