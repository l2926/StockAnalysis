package com.example.springboot.market.vo.vo;

import lombok.Data;

/**
 * @desc 资金流向 vo
 * @author lizijian
 * @date 2025-06-28
 */

@Data
public class MoneyFlowVo {
    private Double buySmVol;
    private Double buyMdVol;
    private Double buyLgVol;
    private Double buyElgVol;

    private Double sellSmVol;
    private Double sellMdVol;
    private Double sellLgVol;
    private Double sellElgVol;

    private Double buySmAmount;
    private Double buyMdAmount;
    private Double buyLgAmount;
    private Double buyElgAmount;

    private Double sellSmAmount;
    private Double sellMdAmount;
    private Double sellLgAmount;
    private Double sellElgAmount;

    private Double netMfAmount;
}
