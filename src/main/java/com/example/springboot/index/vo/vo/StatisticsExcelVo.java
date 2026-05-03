package com.example.springboot.index.vo.vo;

import lombok.Data;

/**
 * 行业行情概览 VO
 */

@Data
public class StatisticsExcelVo {
    private Integer idx;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Integer allCt;
    private Double allMv;
    private Double allAmt;
}
