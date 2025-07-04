package com.example.springboot.market.vo.vo;

import lombok.Data;

/**
 * @desc 资产概览
 * @author lizijian
 * @date 2025-06-12
 */

@Data
public class AssetCenterVo {
    private String endDate;
    private Double totalAssets;
    private Double totalLiab;
    private Double netAssets;
    private Double levelRate;
    private Double totalRevenue;
    private Double totalCogs;
//    private Double operCost;
    private Double nincomeAttrP;
    private Double profitRate;
    private Double roe;
}
