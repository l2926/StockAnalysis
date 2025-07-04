package com.example.springboot.market.vo.req;

import lombok.Data;

/**
 * @author lizijian
 * @desc 市场 入参
 */

@Data
public class MarketReq {
    private Integer selectId;
    private Integer paraId;
    private String tsCode;
    private String symbol;
    private String name;
    private String tradeDate;
    private String startDate;
}
