package com.example.springboot.compare.vo.vo;

import lombok.Data;

/**
 * @author lizijian
 * @desc 单个行情 vo层
 * @date 2025-05-29
 */

@Data
public class DailyVo {
    /**
     * 交易日期
     */
    private String tradeDate;
    /**
     * 股票行情信息
     */
    private Double open;
    private Double close;
    private Double low;
    private Double high;
    private Double amount;
    private Double turnOverRate;
    private Double turnOverRateF;
    private Double pctChg;
    private Double pb;
    private Double pe;
    private Double totalMv;
}
