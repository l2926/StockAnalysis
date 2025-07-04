package com.example.springboot.compare.vo.vo;


import lombok.Data;

/**
 * @author lizijian
 * @desc 对比行情 vo层
 * @date 2025-05-29
 */

@Data
public class DailyCompareVo {
    /**
     * 交易日期
     */
    private String tradeDate;
    /**
     * 第一只股票行情信息
     */
    private Double open1;
    private Double close1;
    private Double low1;
    private Double high1;
    private Double amount1;
    private Double turnOverRate1;
    private Double turnOverRateF1;
    private Double pctChg1;
    private Double pb1;
    private Double pe1;
    private Double totalMv1;
    /**
     * 第二只股票行情信息
     */
    private Double open2;
    private Double close2;
    private Double low2;
    private Double high2;
    private Double amount2;
    private Double turnOverRate2;
    private Double turnOverRateF2;
    private Double pctChg2;
    private Double pb2;
    private Double pe2;
    private Double totalMv2;
}
