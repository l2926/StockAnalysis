package com.example.springboot.market.vo.vo;

import lombok.Data;

/**
 * 获取日线VO层
 */

@Data
public class DailyVo {
    /**
     * 日期
     */
    private String tradeDate;
    /**
     * 开盘价
     */
    private Double open;
    /**
     * 收盘价
     */
    private Double close;
    /**
     * 最低价
     */
    private Double low;
    /**
     * 最高价
     */
    private Double high;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 换手率
     */
    private Double turnOverRate;
    /**
     * 换手率-流通市值
     */
    private Double turnOverRateF;
    /**
     * 涨跌幅
     */
    private Double pctChg;
    /**
     * 量比
     */
    private Double volumeRatio;
    /**
     * 市净率
     */
    private Double pb;
    /**
     * 市盈率
     */
    private Double pe;
    /**
     * 总市值
     */
    private Double totalMv;
}
