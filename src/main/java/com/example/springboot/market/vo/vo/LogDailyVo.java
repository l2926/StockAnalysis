package com.example.springboot.market.vo.vo;

import lombok.Data;

/**
 * @desc 获取日线VO层
 * @author lizijian
 * @date 2025-06-22
 */

@Data
public class LogDailyVo {
    private String tradeDate;
    private Double logOpen;
    private Double logClose;
    private Double logHigh;
    private Double logLow;
    private Double open;
    private Double close;
    private Double low;
    private Double high;
    private Double amount;
    private Double turnOverRate;
    private Double turnOverRateF;
    private Double pctChg;
    private Double volumeRatio;
    private Double pb;
    private Double pe;
    private Double totalMv;
}
