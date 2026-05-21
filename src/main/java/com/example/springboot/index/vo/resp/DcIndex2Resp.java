package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 东方财富概念
 */

@Data
public class DcIndex2Resp {
    private Integer idx;
    private String themeCode;
    private String tradeDate;
    private String name;
    private Double pctChange;
    private Integer zTNum;
    private Double mainChange;
    private String leadStock;
    private String leadStockCode;
    private Double leadStockPctChange;
}
