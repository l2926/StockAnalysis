package com.example.springboot.finance.vo.resp;

import lombok.Data;

/**
 * @desc 财务概览出参
 * @author lizijian
 * @date 2025-06-08
 */

@Data
public class FinanceOverviewResp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String market;
    /**
     * 总资产
     */
    private Double totalAssets;
    /**
     * 总负债
     */
    private Double totalLiab;
    /**
     * 净资产
     */
    private Double netAssets;
    /**
     * 杠杆率
     */
    private Double levelRate;
    /**
     * 营收总收入
     */
    private Double totalRevenue;
    /**
     * 营业收入
     */
    private Double revenue;
    /**
     * 利息收入
     */
    private Double intIncome;
    /**
     * 营业总成本
     */
    private Double totalCogs;
    /**
     * 营业成本
     */
    private Double operCost;
    /**
     * 利息支出
     */
    private Double intExp;
    /**
     * 销售费用
     */
    private Double sellExp;
    /**
     * 管理费用
     */
    private Double adminExp;
    /**
     * 财务费用
     */
    private Double finExp;
    /**
     * 营业利润
     */
    private Double operateProfit;
    /**
     * 利润总额
     */
    private Double totalProfit;
    /**
     * 净利润(含少数股东损益)
     */
    private Double nIncome;
    /**
     * 净利润(不含少数股东损益)
     */
    private Double nIncomeAttrP;
    /**
     * 净利率
     */
    private Double profitRate;
    /**
     * ROE
     */
    private Double roe;
    /**
     * 总市值
     */
    private Double totalMv;
    /**
     * pb
     */
    private Double pb;
    /**
     * 净资产(pb计算)
     */
    private Double assets;
    /**
     * 交易日期
     */
    private String tradeDate;
}
