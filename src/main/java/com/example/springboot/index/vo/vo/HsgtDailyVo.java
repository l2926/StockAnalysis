package com.example.springboot.index.vo.vo;

import lombok.Data;

/**
 * @author lizijian
 * @desc 获取北向资金 vo层
 * @date 2026-05-19
 */

@Data
public class HsgtDailyVo {
    private String tradeDate;
    private Double close;
    private Double open;
    private Double high;
    private Double low;
    private Double pctChg;
    private Double amount;
    private Double turnoverRate;
    private Double peTtm;
    private Double pb;
    private Double northMoney;
}
