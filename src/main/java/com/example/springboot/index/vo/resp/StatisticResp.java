package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * @author lizijian
 * @desc 获取行业行情统计出参
 * @date 2025-03-30
 */

@Data
public class StatisticResp {
    /**
     * 行业code
     */
    private String indexCode;
    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 当日行业涨停个数
     */
    private Integer upCount;
    /**
     * 当日行业指数涨跌幅
     */
    private Double pctChange;
    /**
     * 当日行业跌停个数
     */
    private Integer downCount;
    /**
     * 该行业所有统计数
     */
    private Integer allCount;
}
