package com.example.springboot.market.vo.vo;

import lombok.Data;

/**
 * @desc 财务中心vo层
 * @author lizijian
 * @date 2025-06-11
 */

@Data
public class FinanceCenterVo {
    private String tradeDate;
    private Double pe;
    private Double ps;
    private Double pb;
    private Double totalMv;
    private Double revenue;
    private Double profit;
    private Double asset;
    private Double profitRate;
    private Double roe;
}
