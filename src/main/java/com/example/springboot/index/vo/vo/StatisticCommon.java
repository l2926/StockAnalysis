package com.example.springboot.index.vo.vo;

import lombok.Data;

/**
 * 统计所有的涨跌幅和行业code
 */

@Data
public class StatisticCommon {
    private String tsCode;
    private Double pctChg;
    private String indexCodeL1;
    private String indexCodeL2;
    private String indexCodeL3;
    private Double totalMv;
}
